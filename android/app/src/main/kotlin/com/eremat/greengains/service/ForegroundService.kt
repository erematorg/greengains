package com.eremat.greengains.service

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
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
 * Simple foreground service that shows a notification to the user and provides location updates.
 * Based on: https://github.com/landomen/ForegroundServiceSample
 */
class ForegroundService : Service() {
    private val binder = LocalBinder()

    private val coroutineScope = CoroutineScope(Job())
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    private val _locationFlow = MutableStateFlow<Location?>(null)
    var locationFlow: StateFlow<Location?> = _locationFlow

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
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")

        setupLocationUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")

        // CRITICAL: Always clean up resources to prevent memory leaks and ensure smooth app lifecycle
        running = false
        fusedLocationClient.removeLocationUpdates(locationCallback)
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

    companion object {
        private const val TAG = "GreenGainsFGService"
        private val LOCATION_UPDATES_INTERVAL_MS = 1.seconds.inWholeMilliseconds

        @Volatile
        var running: Boolean = false

        @Volatile
        var methodChannel: MethodChannel? = null
    }
}
