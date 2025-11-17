package com.eremat.greengains.service

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.ServiceCompat
import androidx.core.content.PermissionChecker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.eremat.greengains.notification.NotificationsHelper
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.collections.ArrayDeque
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
 * - Motion sensors coming soon (accelerometer, gyroscope, magnetometer)
 */
class ForegroundService : Service() {
    private val binder = LocalBinder()

    private val coroutineScope = CoroutineScope(Job())

    // Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private val _locationFlow = MutableStateFlow<Location?>(null)
    var locationFlow: StateFlow<Location?> = _locationFlow

    // Sensors
    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null
    private var accelerometerSensor: Sensor? = null
    private var gyroscopeSensor: Sensor? = null
    private val _lightFlow = MutableStateFlow<Float?>(null)
    var lightFlow: StateFlow<Float?> = _lightFlow
    private val _accelerometerFlow = MutableStateFlow<FloatArray?>(null)
    var accelerometerFlow: StateFlow<FloatArray?> = _accelerometerFlow
    private val _gyroscopeFlow = MutableStateFlow<FloatArray?>(null)
    var gyroscopeFlow: StateFlow<FloatArray?> = _gyroscopeFlow

    private val qualityAnalyzer = SensorQualityAnalyzer()

    // Native uploader (runs independently of Flutter)
    private var nativeUploader: NativeBackendUploader? = null
    private var nativeSamplerJob: Job? = null

    // Battery state monitoring for battery-friendly behavior
    private lateinit var batteryMonitor: BatteryStateMonitor

    // Network state monitoring for WiFi-only uploads
    private lateinit var networkMonitor: NetworkStateMonitor


    inner class LocalBinder : Binder() {
        fun getService(): ForegroundService = this@ForegroundService
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d(TAG, "onBind")
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP_SERVICE) {
            stopForegroundService()
            return START_NOT_STICKY
        }
        Log.d(TAG, "onStartCommand")

        startAsForegroundService()

        // Check location permission before starting location updates
        val fineLocationPermission =
            PermissionChecker.checkSelfPermission(this, ACCESS_FINE_LOCATION)
        val coarseLocationPermission =
            PermissionChecker.checkSelfPermission(this, ACCESS_COARSE_LOCATION)

        if (fineLocationPermission == PermissionChecker.PERMISSION_GRANTED ||
            coarseLocationPermission == PermissionChecker.PERMISSION_GRANTED
        ) {
            // Permission granted, start location updates
            startLocationUpdates()
        } else {
            Log.d(TAG, "Location permission not granted, skipping location updates")
        }

        // Always start sensors (they don't require location permission)
        startSensors()
        batteryMonitor.startMonitoring()
        networkMonitor.startMonitoring()
        startNativeUploader()

        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")

        setupLocationUpdates()
        setupSensors()
        batteryMonitor = BatteryStateMonitor(this)
        networkMonitor = NetworkStateMonitor(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")

        // CRITICAL: Always clean up resources to prevent memory leaks and ensure smooth app lifecycle
        running = false
        setServiceEnabledPref(false)
        fusedLocationClient.removeLocationUpdates(locationCallback)
        stopSensors()
        batteryMonitor.stopMonitoring()
        networkMonitor.stopMonitoring()
        stopNativeUploader()

        // Safe notification to Flutter (may be detached)
        try {
            methodChannel?.invokeMethod("onServiceStopped", null)
        } catch (e: Exception) {
            Log.d(TAG, "Flutter already detached, skipping onServiceStopped notification")
        }

        coroutineScope.coroutineContext.cancelChildren()
    }

    /**
     * Promotes the service to a foreground service, showing a notification to the user.
     *
     * CRITICAL: Must be called within 10 seconds of starting the service (onStartCommand) or the
     * system will throw ForegroundServiceDidNotStartInTimeException and kill the service.
     * Calling this immediately in onStartCommand prevents ANR and ensures smooth lifecycle.
     */
    private fun startAsForegroundService() {
        // create the notification channel and notification
        NotificationsHelper.createNotificationChannel(this)
        val notification = NotificationsHelper.buildNotification(this)

        // promote service to foreground service
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

        running = true
        setServiceEnabledPref(true)
        Log.d(TAG, "Service promoted to foreground")
    }

    /**
     * Stops the foreground service and removes the notification.
     * Can be called from inside or outside the service.
     */
    fun stopForegroundService() {
        stopSelf()
        setServiceEnabledPref(false)

        // Safe notification to Flutter (may be detached)
        try {
            methodChannel?.invokeMethod("onServiceStopped", null)
        } catch (e: Exception) {
            Log.d(TAG, "Flutter already detached, skipping onServiceStopped notification")
        }
    }

    /**
     * Sets up the location updates using the FusedLocationProviderClient, but doesn't actually start them.
     * To start the location updates, call [startLocationUpdates].
     */
    private fun setupLocationUpdates() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    _locationFlow.value = location
                    sendLocationToFlutter(location)
                }
            }
        }
    }

    /**
     * Starts the location updates using the FusedLocationProviderClient.
     * Also tries to get last known location for immediate data.
     */
    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        Log.d(TAG, "Starting location updates")

        // Try to get last known location first (faster, uses cached location)
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                Log.d(TAG, "Got last known location: ${location.latitude}, ${location.longitude}")
                _locationFlow.value = location
                sendLocationToFlutter(location)
            } else {
                Log.d(TAG, "No last known location available")
            }
        }.addOnFailureListener { e ->
            Log.w(TAG, "Failed to get last known location: ${e.message}")
        }

        // Start continuous location updates
        fusedLocationClient.requestLocationUpdates(
            LocationRequest.Builder(
                LOCATION_UPDATES_INTERVAL_MS
            ).build(), locationCallback, Looper.getMainLooper()
        )
    }

    /**
     * Sends location update to Flutter via MethodChannel.
     * Only sends if user has enabled Share Location in settings.
     */
    private fun sendLocationToFlutter(location: Location) {
        // Check Share Location preference before sending
        val prefs = getSharedPreferences("FlutterSharedPreferences", Context.MODE_PRIVATE)
        val shareLocation = prefs.getBoolean("flutter.share_location", false)

        if (!shareLocation) {
            // User has disabled location sharing, don't send
            return
        }

        val locationData = mapOf(
            "latitude" to location.latitude,
            "longitude" to location.longitude,
            "accuracy" to location.accuracy.toDouble(),
            "altitude" to if (location.hasAltitude()) location.altitude else null,
            "speed" to if (location.hasSpeed()) location.speed.toDouble() else null,
            "bearing" to if (location.hasBearing()) location.bearing.toDouble() else null,
            "timestamp" to location.time,
            "provider" to location.provider
        )

        postToFlutter("location") { channel ->
            channel.invokeMethod("onLocationUpdate", locationData)
        }
    }

    private fun postToFlutter(action: String, block: (MethodChannel) -> Unit) {
        val channel = methodChannel ?: return
        coroutineScope.launch(Dispatchers.Main) {
            try {
                block(channel)
            } catch (e: Exception) {
                Log.e(TAG, "Error sending $action to Flutter: ${e.message}", e)
            }
        }
    }

    // ============================================
    // SENSOR MANAGEMENT (Light, Motion, etc.)
    // ============================================

    /**
     * Sets up the sensors but doesn't register listeners yet.
     * To start receiving sensor data, call [startSensors].
     */
    private fun setupSensors() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Light sensor (ambient light in lux)
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        if (lightSensor == null) {
            Log.w(TAG, "Light sensor not available on this device")
        } else {
            Log.d(TAG, "Light sensor available: ${lightSensor?.name}")
        }

        // Accelerometer (device acceleration in m/s²)
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (accelerometerSensor == null) {
            Log.w(TAG, "Accelerometer not available on this device")
        } else {
            Log.d(TAG, "Accelerometer available: ${accelerometerSensor?.name}")
        }

        // Gyroscope (device rotation in rad/s)
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        if (gyroscopeSensor == null) {
            Log.w(TAG, "Gyroscope not available on this device")
        } else {
            Log.d(TAG, "Gyroscope available: ${gyroscopeSensor?.name}")
        }
    }

    /**
     * Sensor event listener for all sensors.
     * Following Android best practices: do minimal work in onSensorChanged to avoid blocking.
     */
    private val sensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            when (event.sensor.type) {
                Sensor.TYPE_LIGHT -> {
                    val lux = event.values[0]
                    _lightFlow.value = lux
                    qualityAnalyzer.onLight(lux)
                    sendLightToFlutter(lux)
                }
                Sensor.TYPE_ACCELEROMETER -> {
                    val values = event.values.clone()
                    _accelerometerFlow.value = values
                    qualityAnalyzer.onAccelerometer(values)
                    sendAccelerometerToFlutter(values)
                }
                Sensor.TYPE_GYROSCOPE -> {
                    val values = event.values.clone()
                    _gyroscopeFlow.value = values
                    qualityAnalyzer.onGyroscope(values)
                    sendGyroscopeToFlutter(values)
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // Optional: track sensor accuracy changes
            Log.d(TAG, "Sensor accuracy changed: ${sensor.name}, accuracy=$accuracy")
        }
    }

    /**
     * Starts receiving sensor updates.
     * Uses SENSOR_DELAY_UI (~60ms) for responsive UI updates with reasonable battery life.
     * Data flows instantly to Flutter with no throttling.
     */
    private fun startSensors() {
        lightSensor?.let { sensor ->
            sensorManager.registerListener(
                sensorListener,
                sensor,
                SensorManager.SENSOR_DELAY_UI
            )
            Log.d(TAG, "Light sensor listener registered")
        }

        accelerometerSensor?.let { sensor ->
            sensorManager.registerListener(
                sensorListener,
                sensor,
                SensorManager.SENSOR_DELAY_UI
            )
            Log.d(TAG, "Accelerometer listener registered")
        }

        gyroscopeSensor?.let { sensor ->
            sensorManager.registerListener(
                sensorListener,
                sensor,
                SensorManager.SENSOR_DELAY_UI
            )
            Log.d(TAG, "Gyroscope listener registered")
        }
    }

    /**
     * Stops all sensor listeners.
     * CRITICAL for battery life - always unregister when service stops.
     */
    private fun stopSensors() {
        sensorManager.unregisterListener(sensorListener)
        qualityAnalyzer.reset()
        Log.d(TAG, "All sensor listeners unregistered")
    }

    /**
     * Starts the native uploader and a lightweight sampler that snapshots sensor values every few seconds.
     * The sampler avoids flooding the uploader with high-frequency sensor events while ensuring regular samples.
     */
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

        if (nativeSamplerJob?.isActive == true) {
            return
        }

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

    /**
     * Lightweight analyzer that keeps a rolling window of raw sensor events to derive metadata.
     * Avoids any heavy math (only a few dot products / variances) so it can run 24/7.
     */
    private class SensorQualityAnalyzer {
        private data class MotionSample(val timestamp: Long, val magnitude: Float)
        private data class OrientationInfo(
            val state: OrientationState = OrientationState.UNKNOWN,
            val tiltDegrees: Float? = null
        )

        private val accelSamples = ArrayDeque<MotionSample>()
        private val gyroSamples = ArrayDeque<MotionSample>()
        private var gravityVector = FloatArray(3)
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
            val now = System.currentTimeMillis()
            gyroSamples.addLast(MotionSample(now, magnitude(values)))
            trimOld(now)
        }

        fun buildMetadata(location: Location?): QualityMetadata = synchronized(this) {
            val now = System.currentTimeMillis()
            trimOld(now)

            val orientationInfo = computeOrientation()
            val accelVariance = computeVariance(accelSamples)
            val gyroRms = computeRms(gyroSamples)
            val (motionState, motionConfidence) = classifyMotion(accelVariance, gyroRms)
            val pocketState = determinePocketState(
                orientationInfo,
                lastLux,
                motionState
            )
            val locationQuality = determineLocationQuality(location)

            return QualityMetadata(
                orientation = orientationInfo.state,
                tiltDegrees = orientationInfo.tiltDegrees,
                motionState = motionState,
                motionConfidence = motionConfidence,
                pocketState = pocketState,
                locationQuality = locationQuality
            )
        }

        fun reset() = synchronized(this) {
            accelSamples.clear()
            gyroSamples.clear()
            gravityVector = FloatArray(3)
            gravityInitialized = false
            lastLux = null
        }

        private fun trimOld(now: Long) {
            while (accelSamples.isNotEmpty() && now - accelSamples.first().timestamp > WINDOW_MS) {
                accelSamples.removeFirst()
            }
            while (gyroSamples.isNotEmpty() && now - gyroSamples.first().timestamp > WINDOW_MS) {
                gyroSamples.removeFirst()
            }
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

        private fun computeOrientation(): OrientationInfo {
            if (!gravityInitialized) return OrientationInfo()
            val norm = magnitude(gravityVector)
            if (norm < 0.1f) return OrientationInfo()

            val gx = gravityVector[0] / norm
            val gy = gravityVector[1] / norm
            val gz = gravityVector[2] / norm

            val tiltRad = acos(max(-1f, min(1f, gz)).toDouble())
            val tiltDeg = Math.toDegrees(tiltRad.toDouble()).toFloat()

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
                (diff * diff).toDouble()
            } / samples.size
        }

        private fun computeRms(samples: ArrayDeque<MotionSample>): Double {
            if (samples.isEmpty()) return 0.0
            val meanSquares = samples.sumOf { (it.magnitude * it.magnitude).toDouble() } / samples.size
            return sqrt(meanSquares)
        }

        private fun classifyMotion(
            accelVariance: Double,
            gyroRms: Double
        ): Pair<MotionState, Float> {
            if (accelSamples.isEmpty() && gyroSamples.isEmpty()) {
                return MotionState.UNKNOWN to 0f
            }
            val accelRatio = (accelVariance / ACCEL_MOVING_VARIANCE).toFloat()
            val gyroRatio = (gyroRms / GYRO_MOVING_RMS).toFloat()
            val confidence = max(accelRatio, gyroRatio).coerceIn(0f, 1f)

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
            val ageMs = now - location.time
            if (ageMs > STALE_LOCATION_MS) {
                return LocationQuality.STALE
            }

            val accuracy = if (location.hasAccuracy()) location.accuracy else Float.MAX_VALUE
            return when {
                accuracy <= 25f -> LocationQuality.HIGH
                accuracy <= 75f -> LocationQuality.MEDIUM
                accuracy <= 200f -> LocationQuality.LOW
                else -> LocationQuality.POOR
            }
        }

        private fun magnitude(values: FloatArray): Float {
            return sqrt(
                values[0] * values[0] +
                    values[1] * values[1] +
                    values[2] * values[2]
            )
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

    private fun captureSensorSnapshot(): SensorReading? {
        val light = _lightFlow.value
        val accelValues = _accelerometerFlow.value
        val gyroValues = _gyroscopeFlow.value
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
            location = locationData,
            quality = quality
        )
    }

    private fun handleNativeUploadStatus(event: NativeUploadStatusEvent) {
        Log.d(
            TAG,
            "Native upload event=${event.type} batch=${event.batchSize} buffer=${event.bufferSize} error=${event.errorMessage}"
        )
        sendNativeUploadStatusToFlutter(event)
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

        postToFlutter("native upload status") { channel ->
            channel.invokeMethod("onNativeUploadStatus", payload)
        }
    }

    /**
     * Sends light sensor data to Flutter via MethodChannel.
     */
    private fun sendLightToFlutter(lux: Float) {
        val lightData = mapOf(
            "lux" to lux,
            "timestamp" to System.currentTimeMillis()
        )
        postToFlutter("light") { channel ->
            channel.invokeMethod("onLightUpdate", lightData)
        }
    }

    /**
     * Sends accelerometer data to Flutter via MethodChannel.
     * Values represent acceleration in m/s² along x, y, z axes.
     */
    private fun sendAccelerometerToFlutter(values: FloatArray) {
        val accelData = mapOf(
            "x" to values[0].toDouble(),
            "y" to values[1].toDouble(),
            "z" to values[2].toDouble(),
            "timestamp" to System.currentTimeMillis()
        )
        postToFlutter("accelerometer") { channel ->
            channel.invokeMethod("onAccelerometerUpdate", accelData)
        }
    }

    /**
     * Sends gyroscope data to Flutter via MethodChannel.
     * Values represent rotation rate in rad/s around x, y, z axes.
     */
    private fun sendGyroscopeToFlutter(values: FloatArray) {
        val gyroData = mapOf(
            "x" to values[0].toDouble(),
            "y" to values[1].toDouble(),
            "z" to values[2].toDouble(),
            "timestamp" to System.currentTimeMillis()
        )
        postToFlutter("gyroscope") { channel ->
            channel.invokeMethod("onGyroscopeUpdate", gyroData)
        }
    }

    private fun setServiceEnabledPref(enabled: Boolean) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(PREF_FOREGROUND_ENABLED, enabled).apply()
    }

    companion object {
        private const val TAG = "GreenGainsFGService"
        private val LOCATION_UPDATES_INTERVAL_MS = 1.seconds.inWholeMilliseconds

        // Native uploader sampling interval (independent of Flutter UI updates)
        private const val NATIVE_SAMPLE_INTERVAL_MS = 10_000L

        @Volatile
        var running: Boolean = false

        @Volatile
        var methodChannel: MethodChannel? = null

        const val ACTION_STOP_SERVICE = "com.eremat.greengains.action.STOP_SERVICE"
        private const val PREFS_NAME = "FlutterSharedPreferences"
        private const val PREF_FOREGROUND_ENABLED = "flutter.foreground_service_enabled"
    }
}
