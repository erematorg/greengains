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

    // Native uploader (runs independently of Flutter)
    private var nativeUploader: NativeBackendUploader? = null
    private var nativeSamplerJob: Job? = null

    // Throttling for sensor data to reduce flooding
    private var lastLightValue: Float = 0f
    private var lastLightSentTime: Long = 0L
    private var lastLocationSentTime: Long = 0L
    private var lastAccelerometerSentTime: Long = 0L
    private var lastGyroscopeSentTime: Long = 0L

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
        startNativeUploader()

        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")

        setupLocationUpdates()
        setupSensors()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")

        // CRITICAL: Always clean up resources to prevent memory leaks and ensure smooth app lifecycle
        running = false
        setServiceEnabledPref(false)
        fusedLocationClient.removeLocationUpdates(locationCallback)
        stopSensors()
        stopNativeUploader()
        methodChannel?.invokeMethod("onServiceStopped", null)
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
        methodChannel?.invokeMethod("onServiceStopped", null)
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

                    // Batch location: only send every LOCATION_SEND_INTERVAL_MS
                    val timeDiff = System.currentTimeMillis() - lastLocationSentTime
                    if (timeDiff > LOCATION_SEND_INTERVAL_MS) {
                        sendLocationToFlutter(location)
                        lastLocationSentTime = System.currentTimeMillis()
                    }
                }
            }
        }
    }

    /**
     * Starts the location updates using the FusedLocationProviderClient.
     */
    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        Log.d(TAG, "Starting location updates")
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

                    // Throttle: only send if value changed significantly OR enough time passed
                    val luxDiff = kotlin.math.abs(lux - lastLightValue)
                    val timeDiff = System.currentTimeMillis() - lastLightSentTime

                    if (luxDiff > LIGHT_THRESHOLD_LUX || timeDiff > SENSOR_SEND_INTERVAL_MS) {
                        sendLightToFlutter(lux)
                        lastLightValue = lux
                        lastLightSentTime = System.currentTimeMillis()
                    }
                }
                Sensor.TYPE_ACCELEROMETER -> {
                    val values = event.values.clone()
                    _accelerometerFlow.value = values

                    // Throttle: only send at intervals to avoid flooding
                    val timeDiff = System.currentTimeMillis() - lastAccelerometerSentTime
                    if (timeDiff > SENSOR_SEND_INTERVAL_MS) {
                        sendAccelerometerToFlutter(values)
                        lastAccelerometerSentTime = System.currentTimeMillis()
                    }
                }
                Sensor.TYPE_GYROSCOPE -> {
                    val values = event.values.clone()
                    _gyroscopeFlow.value = values

                    // Throttle: only send at intervals to avoid flooding
                    val timeDiff = System.currentTimeMillis() - lastGyroscopeSentTime
                    if (timeDiff > SENSOR_SEND_INTERVAL_MS) {
                        sendGyroscopeToFlutter(values)
                        lastGyroscopeSentTime = System.currentTimeMillis()
                    }
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
     * Sends initial data immediately so all sensors "light up" simultaneously in UI.
     */
    private fun startSensors() {
        lightSensor?.let { sensor ->
            sensorManager.registerListener(
                sensorListener,
                sensor,
                SensorManager.SENSOR_DELAY_UI
            )
            Log.d(TAG, "Light sensor listener registered")
            // Send initial placeholder data immediately so UI shows sensor is active
            sendLightToFlutter(0f)
        }

        accelerometerSensor?.let { sensor ->
            sensorManager.registerListener(
                sensorListener,
                sensor,
                SensorManager.SENSOR_DELAY_UI
            )
            Log.d(TAG, "Accelerometer listener registered")
            // Send initial placeholder data immediately
            sendAccelerometerToFlutter(floatArrayOf(0f, 0f, 0f))
        }

        gyroscopeSensor?.let { sensor ->
            sensorManager.registerListener(
                sensorListener,
                sensor,
                SensorManager.SENSOR_DELAY_UI
            )
            Log.d(TAG, "Gyroscope listener registered")
            // Send initial placeholder data immediately
            sendGyroscopeToFlutter(floatArrayOf(0f, 0f, 0f))
        }
    }

    /**
     * Stops all sensor listeners.
     * CRITICAL for battery life - always unregister when service stops.
     */
    private fun stopSensors() {
        sensorManager.unregisterListener(sensorListener)
        Log.d(TAG, "All sensor listeners unregistered")
    }

    /**
     * Starts the native uploader and a lightweight sampler that snapshots sensor values every few seconds.
     * The sampler avoids flooding the uploader with high-frequency sensor events while ensuring regular samples.
     */
    private fun startNativeUploader() {
        if (nativeUploader == null) {
            nativeUploader = NativeBackendUploader(
                applicationContext,
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

        if (light == null && accel == null && gyro == null && locationData == null) {
            return null
        }

        return SensorReading(
            timestamp = System.currentTimeMillis(),
            light = light,
            accelerometer = accel,
            gyroscope = gyro,
            location = locationData
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

        // Sensor throttling to reduce flooding
        private const val LIGHT_THRESHOLD_LUX = 10.0f  // Only send if change > 10 lux
        private const val SENSOR_SEND_INTERVAL_MS = 5000L  // Or every 5 seconds
        private const val LOCATION_SEND_INTERVAL_MS = 10000L  // Send location every 10 seconds
        private const val NATIVE_SAMPLE_INTERVAL_MS = 10_000L  // Sample sensors for native uploader every 10 seconds

        @Volatile
        var running: Boolean = false

        @Volatile
        var methodChannel: MethodChannel? = null

        const val ACTION_STOP_SERVICE = "com.eremat.greengains.action.STOP_SERVICE"
        private const val PREFS_NAME = "FlutterSharedPreferences"
        private const val PREF_FOREGROUND_ENABLED = "flutter.foreground_service_enabled"
    }
}
