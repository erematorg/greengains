package com.eremat.greengains.service

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.hardware.SensorManager
import android.location.Location
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.ServiceCompat
import androidx.core.content.PermissionChecker
import com.eremat.greengains.notification.NotificationsHelper
import com.eremat.greengains.service.sensors.Barometer
import com.eremat.greengains.service.sensors.LightSensor
import com.eremat.greengains.service.sensors.MotionSensors
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt
import kotlin.time.Duration.Companion.seconds

/**
 * Simple foreground service that shows a notification to the user and provides sensor data.
 * Based on: https://github.com/landomen/ForegroundServiceSample
 *
 * Sensors:
 * - Location (GPS/Network via FusedLocationProviderClient)
 * - Light (ambient light in lux via SensorManager)
 * - Barometer (pressure in hPa via SensorManager)
 * - Motion sensors (accelerometer, gyroscope via SensorManager)
 */
class ForegroundService : Service() {

    inner class LocalBinder : Binder() {
        fun getService(): ForegroundService = this@ForegroundService
    }

    private val binder = LocalBinder()
    private val coroutineScope = CoroutineScope(Job())

    // Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    // Sensors
    private lateinit var sensorManager: SensorManager
    private lateinit var lightSensor: LightSensor
    private lateinit var barometer: Barometer
    private lateinit var motionSensors: MotionSensors

    // Monitors
    private lateinit var batteryMonitor: BatteryStateMonitor
    private lateinit var networkMonitor: NetworkStateMonitor
    private lateinit var notificationManager: NotificationManager

    // Analysis
    private val qualityAnalyzer = SensorQualityAnalyzer()

    // Native Uploader
    private var nativeUploader: NativeBackendUploader? = null
    private var nativeSamplerJob: Job? = null

    // Data Flows
    private val _lightFlow = MutableStateFlow<Float?>(null)
    private val _pressureFlow = MutableStateFlow<Float?>(null)
    private val _accelerometerFlow = MutableStateFlow<FloatArray?>(null)
    private val _gyroscopeFlow = MutableStateFlow<FloatArray?>(null)
    private val _locationFlow = MutableStateFlow<Location?>(null)

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Initialize Monitors
        batteryMonitor = BatteryStateMonitor(this)
        networkMonitor = NetworkStateMonitor(this)

        // Initialize Sensors
        lightSensor = LightSensor(sensorManager)
        barometer = Barometer(sensorManager)
        motionSensors = MotionSensors(sensorManager)

        // Create Notification Channel (Required for Android O+)
        NotificationsHelper.createNotificationChannel(this)

        setupLocationCallback()
        setupSensorCollectors()
    }

    private fun setupLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    _locationFlow.value = location
                }
            }
        }
    }

    private fun setupSensorCollectors() {
        // Collect from sensor classes and update flows / send to Flutter
        coroutineScope.launch {
            lightSensor.dataFlow.collect { lux ->
                _lightFlow.value = lux
                lux?.let {
                    qualityAnalyzer.onLight(it)
                    sendLightToFlutter(it)
                }
            }
        }

        coroutineScope.launch {
            barometer.dataFlow.collect { hPa ->
                _pressureFlow.value = hPa
                hPa?.let { sendPressureToFlutter(it) }
            }
        }

        coroutineScope.launch {
            motionSensors.accelerometerFlow.collect { values ->
                _accelerometerFlow.value = values
                values?.let {
                    qualityAnalyzer.onAccelerometer(it)
                    sendAccelerometerToFlutter(it)
                }
            }
        }

        coroutineScope.launch {
            motionSensors.gyroscopeFlow.collect { values ->
                _gyroscopeFlow.value = values
                values?.let {
                    qualityAnalyzer.onGyroscope(it)
                    sendGyroscopeToFlutter(it)
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Handle explicit stop action
        if (intent?.action == ACTION_STOP_SERVICE) {
            stopForegroundService()
            return START_NOT_STICKY
        }

        // Handle null intent (service restarted by system after being killed)
        if (intent == null) {
            Log.i(TAG, "Service restarted by system (null intent). Recreating state...")
            // Service was restarted by Android due to START_STICKY
            // All state is preserved in the Service object, just need to ensure it starts
        }

        startForegroundService()
        return START_STICKY
    }

    private fun startForegroundService() {
        if (running) return
        running = true
        setServiceEnabledPref(true)

        // Start Notification
        val notification = NotificationsHelper.buildNotification(this)
        ServiceCompat.startForeground(
            this,
            NotificationsHelper.NOTIFICATION_ID_SERVICE,
            notification,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
            } else {
                0
            }
        )

        // Start Sensors
        lightSensor.start()
        barometer.start()
        motionSensors.start()
        startLocationUpdates()
        
        // Start Native Uploader
        startNativeUploader()
        
        // Start Monitors
        batteryMonitor.startMonitoring()
        networkMonitor.startMonitoring()
    }

    private fun stopForegroundService() {
        running = false
        setServiceEnabledPref(false)

        lightSensor.stop()
        barometer.stop()
        motionSensors.stop()
        stopLocationUpdates()
        stopNativeUploader()
        
        batteryMonitor.stopMonitoring()
        networkMonitor.stopMonitoring()

        stopForeground(true)
        stopSelf()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        val hasFine = PermissionChecker.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PermissionChecker.PERMISSION_GRANTED
        val hasCoarse = PermissionChecker.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PermissionChecker.PERMISSION_GRANTED

        if (!hasFine && !hasCoarse) {
            Log.w(TAG, "No location permission")
            return
        }

        val request = LocationRequest.create().apply {
            interval = LOCATION_UPDATES_INTERVAL_MS
            fastestInterval = LOCATION_UPDATES_INTERVAL_MS / 2
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        fusedLocationClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun startNativeUploader() {
        if (nativeUploader == null) {
            nativeUploader = NativeBackendUploader(
                context = applicationContext,
                batteryMonitor = batteryMonitor,
                networkMonitor = networkMonitor,
                statusListener = ::handleNativeUploadStatus
            )
        }
        nativeUploader?.start()

        if (nativeSamplerJob?.isActive == true) return

        nativeSamplerJob = coroutineScope.launch(Dispatchers.Default) {
            while (isActive) {
                val snapshot = captureSensorSnapshot()
                if (snapshot != null) {
                    nativeUploader?.addReading(snapshot)
                }
                delay(NATIVE_SAMPLE_INTERVAL_MS)
            }
        }
    }

    private fun stopNativeUploader() {
        nativeSamplerJob?.cancel()
        nativeSamplerJob = null
        nativeUploader?.stop()
        nativeUploader = null
    }

    private fun captureSensorSnapshot(): SensorReading? {
        val light = _lightFlow.value
        val accelValues = _accelerometerFlow.value
        val gyroValues = _gyroscopeFlow.value
        val pressure = _pressureFlow.value
        val location = _locationFlow.value

        val accel = accelValues?.takeIf { it.size >= 3 }?.let {
            AccelData(it[0], it[1], it[2])
        }
        val gyro = gyroValues?.takeIf { it.size >= 3 }?.let {
            GyroData(it[0], it[1], it[2])
        }
        val locationData = location?.let {
            LocationData(
                latitude = it.latitude,
                longitude = it.longitude,
                accuracy = if (it.hasAccuracy()) it.accuracy.toDouble() else null
            )
        }
        val quality = qualityAnalyzer.buildMetadata(location)

        if (light == null && accel == null && gyro == null && locationData == null) {
            return null
        }

        return SensorReading(
            timestamp = System.currentTimeMillis(),
            light = light,
            accelerometer = accel,
            gyroscope = gyro,
            pressure = pressure,
            location = locationData,
            quality = quality
        )
    }

    private fun handleNativeUploadStatus(event: NativeUploadStatusEvent) {
        Log.d(TAG, "Native upload: ${event.type}")
        sendNativeUploadStatusToFlutter(event)
        if (event.type == NativeUploadEventType.SUCCESS && ::notificationManager.isInitialized) {
            NotificationsHelper.notifyUpdate(this, notificationManager, event.timestamp)
        }
    }

    private fun sendNativeUploadStatusToFlutter(event: NativeUploadStatusEvent) {
        val status = when (event.type) {
            NativeUploadEventType.STARTED -> "started"
            NativeUploadEventType.SUCCESS -> "success"
            NativeUploadEventType.FAILURE -> "failure"
        }
        val payload = mutableMapOf<String, Any>(
            "status" to status,
            "timestamp" to event.timestamp,
            "batchSize" to event.batchSize,
            "bufferSize" to event.bufferSize
        )
        event.errorMessage?.let { payload["error"] = it }
        
        postToFlutter("native upload status") { it.invokeMethod("onNativeUploadStatus", payload) }
    }

    private fun sendLightToFlutter(lux: Float) {
        postToFlutter("light") { it.invokeMethod("onLightUpdate", mapOf("lux" to lux, "timestamp" to System.currentTimeMillis())) }
    }

    private fun sendAccelerometerToFlutter(values: FloatArray) {
        postToFlutter("accelerometer") { 
            it.invokeMethod("onAccelerometerUpdate", mapOf(
                "x" to values[0].toDouble(),
                "y" to values[1].toDouble(),
                "z" to values[2].toDouble(),
                "timestamp" to System.currentTimeMillis()
            )) 
        }
    }

    private fun sendGyroscopeToFlutter(values: FloatArray) {
        postToFlutter("gyroscope") { 
            it.invokeMethod("onGyroscopeUpdate", mapOf(
                "x" to values[0].toDouble(),
                "y" to values[1].toDouble(),
                "z" to values[2].toDouble(),
                "timestamp" to System.currentTimeMillis()
            )) 
        }
    }

    private fun sendPressureToFlutter(hPa: Float) {
        postToFlutter("pressure") { 
            it.invokeMethod("onPressureUpdate", mapOf(
                "hPa" to hPa,
                "timestamp" to System.currentTimeMillis()
            )) 
        }
    }

    private fun postToFlutter(tag: String, block: (MethodChannel) -> Unit) {
        val channel = methodChannel ?: return
        // Use Main Looper to invoke method channel
        android.os.Handler(Looper.getMainLooper()).post {
            try {
                block(channel)
            } catch (e: Exception) {
                Log.e(TAG, "Error sending $tag to Flutter", e)
            }
        }
    }

    private fun setServiceEnabledPref(enabled: Boolean) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(PREF_FOREGROUND_ENABLED, enabled).apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForegroundService()
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    // Inner class for analysis
    private class SensorQualityAnalyzer {
        private data class MotionSample(val timestamp: Long, val magnitude: Float)
        private data class OrientationInfo(
            val state: OrientationState = OrientationState.UNKNOWN,
            val tiltDegrees: Float? = null
        )

        private val accelSamples = ArrayDeque<MotionSample>()
        private val gravityVector = FloatArray(3)
        private var gravityInitialized = false
        private var lastLux: Float? = null

        fun onLight(lux: Float) = synchronized(this) {
            lastLux = lux
        }

        fun onAccelerometer(values: FloatArray) = synchronized(this) {
            val now = System.currentTimeMillis()
            updateGravity(values)
            accelSamples.addLast(MotionSample(now, magnitude(values)))
            trimOld(now)
        }

        fun onGyroscope(values: FloatArray) = synchronized(this) {
            // Gyro logic if needed
        }

        fun buildMetadata(location: Location?): QualityMetadata = synchronized(this) {
             val orientationInfo = computeOrientation()
             val (motionState, motionConfidence) = classifyMotion(
                 accelVariance = computeVariance(accelSamples),
                 gyroRms = 0.0 // We aren't tracking gyro samples yet in this analyzer
             )
             val pocketState = determinePocketState(orientationInfo, lastLux, motionState)
             
             return QualityMetadata(
                 orientation = orientationInfo.state,
                 tiltDegrees = orientationInfo.tiltDegrees,
                 motionState = motionState,
                 motionConfidence = motionConfidence,
                 pocketState = pocketState,
                 locationQuality = determineLocationQuality(location)
             )
        }
        
        private fun updateGravity(values: FloatArray) {
            if (!gravityInitialized) {
                gravityVector[0] = values[0]
                gravityVector[1] = values[1]
                gravityVector[2] = values[2]
                gravityInitialized = true
                return
            }
            gravityVector[0] = ALPHA * values[0] + (1 - ALPHA) * gravityVector[0]
            gravityVector[1] = ALPHA * values[1] + (1 - ALPHA) * gravityVector[1]
            gravityVector[2] = ALPHA * values[2] + (1 - ALPHA) * gravityVector[2]
        }
        
        private fun trimOld(now: Long) {
            while (accelSamples.isNotEmpty() && now - accelSamples.first().timestamp > WINDOW_MS) {
                accelSamples.removeFirst()
            }
        }
        
        private fun magnitude(values: FloatArray): Float {
            return sqrt(values[0] * values[0] + values[1] * values[1] + values[2] * values[2])
        }
        
        private fun computeOrientation(): OrientationInfo {
            if (!gravityInitialized) return OrientationInfo()
            val norm = magnitude(gravityVector)
            if (norm < 0.1f) return OrientationInfo()

            val gx = gravityVector[0] / norm
            val gy = gravityVector[1] / norm
            val gz = gravityVector[2] / norm

            val tiltRad = acos(max(-1f, min(1f, gz)).toDouble())
            val tiltDeg = Math.toDegrees(tiltRad).toFloat()

            val absGz = abs(gz)
            val absGx = abs(gx)
            val absGy = abs(gy)

            val state = when {
                gz <= -FACE_THRESHOLD -> OrientationState.FACE_UP
                gz >= FACE_THRESHOLD -> OrientationState.FACE_DOWN
                absGz < UPRIGHT_THRESHOLD -> {
                    when {
                        absGy - absGx > ORIENTATION_MARGIN -> OrientationState.UPRIGHT_PORTRAIT
                        absGx - absGy > ORIENTATION_MARGIN -> OrientationState.UPRIGHT_LANDSCAPE
                        else -> OrientationState.UPRIGHT_UNKNOWN
                    }
                }
                else -> OrientationState.UNKNOWN
            }

            return OrientationInfo(state = state, tiltDegrees = tiltDeg)
        }
        
        private fun computeVariance(samples: ArrayDeque<MotionSample>): Double {
            if (samples.size < 2) return 0.0
            val mean = samples.sumOf { it.magnitude.toDouble() } / samples.size
            return samples.sumOf {
                val diff = it.magnitude - mean
                (diff * diff)
            } / samples.size
        }
        
        private fun classifyMotion(
            accelVariance: Double,
            gyroRms: Double
        ): Pair<MotionState, Float> {
            if (accelSamples.isEmpty()) {
                return MotionState.UNKNOWN to 0f
            }
            val accelRatio = (accelVariance / ACCEL_MOVING_VARIANCE).toFloat()
            // val gyroRatio = (gyroRms / GYRO_MOVING_RMS).toFloat()
            // val confidence = max(accelRatio, gyroRatio).coerceIn(0f, 1f)
            val confidence = accelRatio.coerceIn(0f, 1f)

            val state = when {
                confidence < 0.2f -> MotionState.STATIONARY
                confidence < 0.6f -> MotionState.LIGHT
                else -> MotionState.ACTIVE
            }
            return state to confidence
        }
        
        private fun determinePocketState(
            orientationInfo: OrientationInfo,
            lux: Float?,
            motionState: MotionState
        ): PocketState {
            val tilt = orientationInfo.tiltDegrees
            if (lux == null || tilt == null) {
                return PocketState.UNKNOWN
            }

            return when {
                lux < POCKET_LUX_THRESHOLD &&
                    tilt in POCKET_TILT_MIN..POCKET_TILT_MAX &&
                    motionState != MotionState.ACTIVE -> PocketState.LIKELY
                lux > BRIGHT_ENV_LUX -> PocketState.UNLIKELY
                else -> PocketState.UNKNOWN
            }
        }

        private fun determineLocationQuality(location: Location?): LocationQuality {
            location ?: return LocationQuality.NONE
            val now = System.currentTimeMillis()
            if (now - location.time > STALE_LOCATION_MS) return LocationQuality.STALE
            return when {
                location.accuracy <= 25f -> LocationQuality.HIGH
                location.accuracy <= 75f -> LocationQuality.MEDIUM
                location.accuracy <= 200f -> LocationQuality.LOW
                else -> LocationQuality.POOR
            }
        }

        companion object {
            private const val WINDOW_MS = 10_000L
            private const val ALPHA = 0.1f
            private const val FACE_THRESHOLD = 0.7f
            private const val UPRIGHT_THRESHOLD = 0.4f
            private const val ORIENTATION_MARGIN = 0.15f
            private const val ACCEL_MOVING_VARIANCE = 0.5
            private const val GYRO_MOVING_RMS = 0.07
            private const val POCKET_LUX_THRESHOLD = 2f
            private const val BRIGHT_ENV_LUX = 15f
            private const val POCKET_TILT_MIN = 60f
            private const val POCKET_TILT_MAX = 120f
            private const val STALE_LOCATION_MS = 60_000L
        }
    }

    companion object {
        private const val TAG = "GreenGainsFGService"
        private val LOCATION_UPDATES_INTERVAL_MS = 1.seconds.inWholeMilliseconds
        private const val NATIVE_SAMPLE_INTERVAL_MS = 10_000L
        const val ACTION_STOP_SERVICE = "com.eremat.greengains.action.STOP_SERVICE"
        private const val PREFS_NAME = "FlutterSharedPreferences"
        private const val PREF_FOREGROUND_ENABLED = "flutter.foreground_service_enabled"

        @Volatile var running: Boolean = false
        @Volatile var methodChannel: MethodChannel? = null
    }
}
