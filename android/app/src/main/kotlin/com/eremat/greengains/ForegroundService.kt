package com.eremat.greengains

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class ForegroundService : Service() {

    companion object {
        const val TAG = "GreenGainsFGService"
        const val CHANNEL_ID = "greengains.foreground"
        const val CHANNEL_NAME = "Background Service"
        const val NOTIFICATION_ID = 1001
        @Volatile var running: Boolean = false
        @Volatile var methodChannel: MethodChannel? = null

        private val LOCATION_UPDATE_INTERVAL = 10.seconds
        private const val MIN_DISTANCE_METERS = 10f // 10 meters
        private const val MIN_TIME_MS = 5000L // 5 seconds
    }

    private val coroutineScope = CoroutineScope(Job())
    private var tickerJob: Job? = null

    // Location tracking
    private var locationManager: LocationManager? = null
    private var lastLocation: Location? = null
    private var lastLocationUpdateTime: Long = 0

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.d(TAG, "Location update: lat=${location.latitude}, lon=${location.longitude}, accuracy=${location.accuracy}m")
            lastLocation = location
            lastLocationUpdateTime = System.currentTimeMillis()
            sendLocationToFlutter(location)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            Log.d(TAG, "Location status changed: provider=$provider, status=$status")
        }

        override fun onProviderEnabled(provider: String) {
            Log.d(TAG, "Location provider enabled: $provider")
        }

        override fun onProviderDisabled(provider: String) {
            Log.d(TAG, "Location provider disabled: $provider")
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: Service created")
        createChannel()
        startLocationTracking()
        startPeriodicSensorTrigger()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: Starting foreground service")
        val notification = buildNotification()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            ServiceCompat.startForeground(
                this,
                NOTIFICATION_ID,
                notification,
                android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC or
                android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
            )
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }

        running = true
        Log.d(TAG, "onStartCommand: Service is now running")
        return START_STICKY
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Service being destroyed")
        running = false
        stopLocationTracking()
        tickerJob?.cancel()
        coroutineScope.coroutineContext.cancelChildren()
        super.onDestroy()
        Log.d(TAG, "onDestroy: Service destroyed")
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun startLocationTracking() {
        Log.d(TAG, "startLocationTracking: Initializing location tracking")

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // Check permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.w(TAG, "Location permissions not granted")
            return
        }

        try {
            // Try GPS first (FINE location ~10-50m)
            if (locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true) {
                Log.d(TAG, "GPS provider enabled, requesting location updates")
                locationManager?.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    MIN_TIME_MS,
                    MIN_DISTANCE_METERS,
                    locationListener
                )
                // Get last known location
                locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let {
                    Log.d(TAG, "Got last known GPS location")
                    lastLocation = it
                    sendLocationToFlutter(it)
                }
            }

            // Also use Network provider (COARSE location ~100-500m fallback)
            if (locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) == true) {
                Log.d(TAG, "Network provider enabled, requesting location updates")
                locationManager?.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    MIN_TIME_MS,
                    MIN_DISTANCE_METERS,
                    locationListener
                )
                // Get last known location if we don't have GPS
                if (lastLocation == null) {
                    locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)?.let {
                        Log.d(TAG, "Got last known Network location")
                        lastLocation = it
                        sendLocationToFlutter(it)
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Security exception when requesting location: ${e.message}")
        } catch (e: Exception) {
            Log.e(TAG, "Error starting location tracking: ${e.message}", e)
        }
    }

    private fun stopLocationTracking() {
        Log.d(TAG, "stopLocationTracking: Stopping location updates")
        try {
            locationManager?.removeUpdates(locationListener)
            locationManager = null
        } catch (e: Exception) {
            Log.e(TAG, "Error stopping location tracking: ${e.message}", e)
        }
    }

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
                Log.d(TAG, "Sending location to Flutter: lat=${location.latitude}, lon=${location.longitude}")
                channel.invokeMethod("onLocationUpdate", locationData)
            } catch (e: Exception) {
                Log.e(TAG, "Error sending location to Flutter: ${e.message}", e)
            }
        }
    }

    private fun startPeriodicSensorTrigger() {
        Log.d(TAG, "startPeriodicSensorTrigger: Starting coroutine ticker")
        tickerJob = coroutineScope.launch {
            tickerFlow(LOCATION_UPDATE_INTERVAL, LOCATION_UPDATE_INTERVAL)
                .collectLatest {
                    triggerDartSensorCollection()
                }
        }
    }

    private suspend fun triggerDartSensorCollection() {
        withContext(Dispatchers.Main) {
            val channel = methodChannel
            if (channel == null) {
                Log.w(TAG, "MethodChannel is null, cannot trigger sensor collection")
                return@withContext
            }

            try {
                Log.d(TAG, "Triggering Dart sensor collection...")
                channel.invokeMethod("collectSensors", null)
            } catch (e: Exception) {
                Log.e(TAG, "Error invoking collectSensors on Dart: ${e.message}", e)
            }
        }
    }

    private fun tickerFlow(period: Duration, initialDelay: Duration): Flow<Unit> = flow {
        delay(initialDelay)
        while (true) {
            emit(Unit)
            delay(period)
        }
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT,
            ).apply {
                setShowBadge(false)
            }
            manager.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {
        val launchIntent = Intent(this@ForegroundService, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getActivity(
            this@ForegroundService,
            0,
            launchIntent,
            pendingIntentFlags,
        )

        val contentText = if (lastLocation != null) {
            "Tracking location (±${lastLocation?.accuracy?.toInt()}m)"
        } else {
            "Waiting for location..."
        }

        return NotificationCompat.Builder(this@ForegroundService, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_compass)
            .setContentTitle("GreenGains Location Tracking")
            .setContentText(contentText)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setShowWhen(false)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }
}
