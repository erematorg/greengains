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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private val _lightFlow = MutableStateFlow<Float?>(null)
    var lightFlow: StateFlow<Float?> = _lightFlow

    // Throttling for sensor data to reduce flooding
    private var lastLightValue: Float = 0f
    private var lastLightSentTime: Long = 0L

    inner class LocalBinder : Binder() {
        fun getService(): ForegroundService = this@ForegroundService
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d(TAG, "onBind")
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")

        val fineLocationPermission =
            PermissionChecker.checkSelfPermission(this, ACCESS_FINE_LOCATION)
        val coarseLocationPermission =
            PermissionChecker.checkSelfPermission(this, ACCESS_COARSE_LOCATION)
        if (fineLocationPermission != PermissionChecker.PERMISSION_GRANTED &&
            coarseLocationPermission != PermissionChecker.PERMISSION_GRANTED
        ) {
            // stop the service if we don't have the necessary permissions
            Log.d(TAG, "Required permissions have not been granted! Stopping service.")
            stopSelf()
        } else {
            startAsForegroundService()
            startLocationUpdates()
            startSensors()
        }

        return super.onStartCommand(intent, flags, startId)
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
        fusedLocationClient.removeLocationUpdates(locationCallback)
        stopSensors()
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
        Log.d(TAG, "Service promoted to foreground")
    }

    /**
     * Stops the foreground service and removes the notification.
     * Can be called from inside or outside the service.
     */
    fun stopForegroundService() {
        stopSelf()
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
     */
    private fun sendLocationToFlutter(location: Location) {
        coroutineScope.launch(Dispatchers.Main) {
            val channel = methodChannel
            if (channel == null) {
                Log.w(TAG, "MethodChannel is null, cannot send location")
                return@launch
            }

            try {
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
                Log.d(TAG, "Sending location to Flutter: lat=${location.latitude}, lon=${location.longitude}, accuracy=${location.accuracy}m")
                channel.invokeMethod("onLocationUpdate", locationData)
            } catch (e: Exception) {
                Log.e(TAG, "Error sending location to Flutter: ${e.message}", e)
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
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // Optional: track sensor accuracy changes
            Log.d(TAG, "Sensor accuracy changed: ${sensor.name}, accuracy=$accuracy")
        }
    }

    /**
     * Starts receiving sensor updates.
     * Uses SENSOR_DELAY_NORMAL (~200ms) for good battery life.
     */
    private fun startSensors() {
        lightSensor?.let { sensor ->
            sensorManager.registerListener(
                sensorListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
            Log.d(TAG, "Light sensor listener registered")
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
     * Sends light sensor data to Flutter via MethodChannel.
     */
    private fun sendLightToFlutter(lux: Float) {
        coroutineScope.launch(Dispatchers.Main) {
            val channel = methodChannel
            if (channel == null) {
                Log.w(TAG, "MethodChannel is null, cannot send light data")
                return@launch
            }

            try {
                val lightData = mapOf(
                    "lux" to lux,
                    "timestamp" to System.currentTimeMillis()
                )
                Log.d(TAG, "Sending light data to Flutter: ${lux.toInt()} lux")
                channel.invokeMethod("onLightUpdate", lightData)
            } catch (e: Exception) {
                Log.e(TAG, "Error sending light data to Flutter: ${e.message}", e)
            }
        }
    }

    companion object {
        private const val TAG = "GreenGainsFGService"
        private val LOCATION_UPDATES_INTERVAL_MS = 1.seconds.inWholeMilliseconds

        // Sensor throttling to reduce flooding
        private const val LIGHT_THRESHOLD_LUX = 5.0f  // Only send if change > 5 lux
        private const val SENSOR_SEND_INTERVAL_MS = 2000L  // Or every 2 seconds

        @Volatile
        var running: Boolean = false

        @Volatile
        var methodChannel: MethodChannel? = null
    }
}
