package com.eremat.greengains

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

/**
 * MainActivity - copied from ForegroundServiceSamples
 * Handles permission requests and service lifecycle
 */
class MainActivity : FlutterActivity() {

    // we need notification permission to be able to display a notification for the foreground service
    private val notificationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            // if permission was denied, the service can still run only the notification won't be visible
        }

    // we need location permission to be able to start the service
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted, service can run
                startForegroundService()
            }

            permissions.getOrDefault(ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted, service can still run.
                startForegroundService()
            }

            else -> {
                // No location access granted, service can't be started as it will crash
                Toast.makeText(this, "Location permission is required!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkAndRequestNotificationPermission()
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        // Create sensor trigger channel and pass to ForegroundService
        val sensorTriggerChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "greengains/sensor_trigger")
        ForegroundService.methodChannel = sensorTriggerChannel

        // Flutter → Native: Start/Stop foreground service
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "greengains/foreground").setMethodCallHandler { call, result ->
            when (call.method) {
                "startForegroundService" -> {
                    // Request location permission first (like their sample)
                    locationPermissionRequest.launch(
                        arrayOf(
                            ACCESS_FINE_LOCATION,
                            ACCESS_COARSE_LOCATION
                        )
                    )
                    result.success(true)
                }
                "stopForegroundService" -> {
                    val ok = stopFgService()
                    result.success(ok)
                }
                "isForegroundServiceRunning" -> {
                    result.success(ForegroundService.running)
                }
                else -> result.notImplemented()
            }
        }
    }

    /**
     * Check for notification permission before starting the service so that the notification is visible
     */
    private fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            )) {
                android.content.pm.PackageManager.PERMISSION_GRANTED -> {
                    // permission already granted
                }

                else -> {
                    notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

    /**
     * Creates and starts the ForegroundService as a foreground service.
     */
    private fun startForegroundService() {
        // start the service
        val serviceIntent = Intent(this, ForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
    }

    private fun stopFgService(): Boolean {
        return try {
            val intent = Intent(this, ForegroundService::class.java)
            stopService(intent)
            true
        } catch (_: Exception) {
            false
        }
    }
}
