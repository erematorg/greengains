package com.eremat.greengains.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.eremat.greengains.service.ForegroundService

/**
 * Receives device boot events and automatically restarts the foreground service
 * if it was enabled before device shutdown/reboot.
 *
 * This ensures passive tracking continues without user intervention after reboot.
 */
class BootReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "BootReceiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) {
            return
        }

        Log.i(TAG, "Device boot detected, checking if service should auto-start")

        // Check if foreground service was enabled before reboot
        val prefs = context.getSharedPreferences("FlutterSharedPreferences", Context.MODE_PRIVATE)
        val serviceEnabled = prefs.getBoolean("flutter.foreground_service_enabled", false)

        if (!serviceEnabled) {
            Log.i(TAG, "Service was disabled before reboot, skipping auto-start")
            return
        }

        Log.i(TAG, "Auto-starting foreground service after boot")

        try {
            val serviceIntent = Intent(context, ForegroundService::class.java)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent)
            } else {
                context.startService(serviceIntent)
            }

            Log.i(TAG, "Foreground service started successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to start foreground service after boot", e)
        }
    }
}
