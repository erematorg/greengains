package com.eremat.greengains

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.PermissionChecker
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
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

/**
 * Foreground service that tracks location and sends updates to Flutter.
 *
 * Based on: https://github.com/landomen/ForegroundServiceSample
 * Pattern: Promotes to foreground immediately, uses FusedLocationProviderClient,
 * cleans up resources in onDestroy.
 */
class ForegroundService : Service() {

    companion object {
        const val TAG = "GreenGainsFGService"
        const val CHANNEL_ID = "greengains.foreground"
        const val CHANNEL_NAME = "Location Tracking"
        const val NOTIFICATION_ID = 1001

        @Volatile
        var running: Boolean = false

        @Volatile
        var methodChannel: MethodChannel? = null

        private val LOCATION_UPDATES_INTERVAL_MS = 1.seconds.inWholeMilliseconds
    }

    private val coroutineScope = CoroutineScope(Job())
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var lastLocation: Location? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")

        setupLocationUpdates()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")

        // Check permissions - if not granted, stop the service
        val fineLocationPermission = PermissionChecker.checkSelfPermission(this, ACCESS_FINE_LOCATION)
        val coarseLocationPermission = PermissionChecker.checkSelfPermission(this, ACCESS_COARSE_LOCATION)

        if (fineLocationPermission != PermissionChecker.PERMISSION_GRANTED &&
            coarseLocationPermission != PermissionChecker.PERMISSION_GRANTED) {
            // Stop the service if we don't have the necessary permissions
            Log.d(TAG, "Required permissions have not been granted! Stopping service.")
            stopSelf()
        } else {
            startAsForegroundService()
            startLocationUpdates()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")

        // CRITICAL: Always clean up resources to prevent memory leaks
        running = false
        fusedLocationClient.removeLocationUpdates(locationCallback)
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    /**
     * Promotes the service to a foreground service, showing a notification to the user.
     *
     * CRITICAL: Must be called within 10 seconds of starting the service (onStartCommand) or the
     * system will throw ForegroundServiceDidNotStartInTimeException and kill the service.
     */
    private fun startAsForegroundService() {
        createChannel()
        val notification = buildNotification()

        // Promote service to foreground service
        ServiceCompat.startForeground(
            this,
            NOTIFICATION_ID,
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
     * Sets up the location updates using the FusedLocationProviderClient.
     * Doesn't actually start them - call startLocationUpdates() for that.
     */
    private fun setupLocationUpdates() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    lastLocation = location
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
            LocationRequest.Builder(LOCATION_UPDATES_INTERVAL_MS).build(),
            locationCallback,
            Looper.getMainLooper()
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

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW,
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
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }
}
