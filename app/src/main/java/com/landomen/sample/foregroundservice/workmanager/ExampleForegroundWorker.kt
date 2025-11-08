package com.landomen.sample.foregroundservice.workmanager

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.core.content.PermissionChecker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.landomen.sample.foregroundservice.notification.NotificationsHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * Simple long-running CoroutineWorker that uses a foreground service underneath.
 */
class ExampleForegroundWorker(
    private val appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    private val coroutineScope = CoroutineScope(Job())
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    override suspend fun doWork(): Result {
        if (!hasNecessaryRuntimePermissions()) {
            // stop the worker if we don't have the necessary permissions
            Log.d(TAG, "Required permissions have not been granted! Stopping worker.")
            return Result.failure()
        }

        try {
            setForeground(createForegroundInfo())
            setupLocationUpdates()
            startLocationUpdates()
        } catch (e: Exception) {
            Log.e(TAG, "Error setting foreground Worker ${e.message}")
        }

        // simulate long running work by receiving location updates for 10 seconds
        delay(WORKER_DURATION_MS)
        return Result.success()
    }

    private fun createForegroundInfo(): ForegroundInfo {
        NotificationsHelper.createNotificationChannel(appContext)
        val notification = NotificationsHelper.buildWorkerNotification(appContext)
        return ForegroundInfo(
            2, // notification ID
            notification, // Notification object
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
            } else {
                0
            }
        )
    }

    private fun hasNecessaryRuntimePermissions(): Boolean {
        val fineLocationPermission =
            PermissionChecker.checkSelfPermission(
                appContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        val coarseLocationPermission =
            PermissionChecker.checkSelfPermission(
                appContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )

        return fineLocationPermission == PermissionChecker.PERMISSION_GRANTED &&
                coarseLocationPermission == PermissionChecker.PERMISSION_GRANTED
    }

    /**
     * Sets up the location updates using the FusedLocationProviderClient, but doesn't actually start them.
     * To start the location updates, call [startLocationUpdates].
     */
    private fun setupLocationUpdates() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(appContext)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    coroutineScope.launch {
                        setProgress(
                            workDataOf(
                                DATA_KEY_LOCATION_LAT to location.latitude,
                                DATA_KEY_LOCATION_LNG to location.longitude
                            )
                        )
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
        fusedLocationClient.requestLocationUpdates(
            LocationRequest.Builder(
                LOCATION_UPDATES_INTERVAL_MS
            ).build(), locationCallback, Looper.getMainLooper()
        )
    }

    companion object {
        private const val TAG = "ForegroundWorker"
        private val LOCATION_UPDATES_INTERVAL_MS = 1.seconds.inWholeMilliseconds
        const val DATA_KEY_LOCATION_LAT = "location_lat"
        const val DATA_KEY_LOCATION_LNG = "location_lng"
        private val WORKER_DURATION_MS = 15.minutes.inWholeMilliseconds
    }
}