package com.eremat.greengains

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import com.eremat.greengains.service.ForegroundService

/**
 * MainActivity - based on ForegroundServiceSamples pattern
 * Handles permission requests and service lifecycle
 *
 * Pattern differences from ComponentActivity sample:
 * - FlutterActivity doesn't support ActivityResultContracts
 * - Uses older requestPermissions() API for compatibility
 * - MethodChannel for Flutter communication instead of Binder/ServiceConnection
 */
class MainActivity : FlutterActivity() {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
        private const val NOTIFICATION_PERMISSION_REQUEST_CODE = 1002
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
                    requestLocationPermissions()
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
                PackageManager.PERMISSION_GRANTED -> {
                    // permission already granted
                }

                else -> {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                        NOTIFICATION_PERMISSION_REQUEST_CODE
                    )
                }
            }
        }
    }

    /**
     * Request location permissions (FINE + COARSE)
     * Using older API since FlutterActivity doesn't support ActivityResultContracts
     */
    private fun requestLocationPermissions() {
        val fineGranted = ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
        val coarseGranted = ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)

        if (fineGranted == PackageManager.PERMISSION_GRANTED ||
            coarseGranted == PackageManager.PERMISSION_GRANTED) {
            // At least one permission granted, start service
            startForegroundService()
        } else {
            // Request permissions
            ActivityCompat.requestPermissions(
                this,
                arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    /**
     * Handle permission request results
     * Following their pattern: show Toast on denial, start service on grant
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                val fineGranted = grantResults.getOrNull(0) == PackageManager.PERMISSION_GRANTED
                val coarseGranted = grantResults.getOrNull(1) == PackageManager.PERMISSION_GRANTED

                when {
                    fineGranted -> {
                        // Precise location access granted, service can run
                        startForegroundService()
                    }
                    coarseGranted -> {
                        // Only approximate location access granted, service can still run
                        startForegroundService()
                    }
                    else -> {
                        // No location access granted, service can't be started as it will crash
                        // Following their pattern: show Toast (not dialog, not forced Settings)
                        Toast.makeText(this, "Location permission is required!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            NOTIFICATION_PERMISSION_REQUEST_CODE -> {
                // if permission was denied, the service can still run only the notification won't be visible
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
