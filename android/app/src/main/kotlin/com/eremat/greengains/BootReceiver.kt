package com.eremat.greengains

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.eremat.greengains.service.ForegroundService

/**
 * BroadcastReceiver that restarts the ForegroundService after device reboot
 * if the service was running before.
 *
 * This ensures continuous tracking even after device restarts.
 */
class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) {
            return
        }

        Log.i(TAG, "Device boot completed. Checking if service should restart...")

        // Check if service was enabled before reboot
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val wasServiceRunning = prefs.getBoolean(PREF_FOREGROUND_ENABLED, false)

        if (wasServiceRunning) {
            Log.i(TAG, "Service was running before reboot. Restarting...")

            val serviceIntent = Intent(context, ForegroundService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent)
            } else {
                context.startService(serviceIntent)
            }
        } else {
            Log.i(TAG, "Service was not running before reboot. Skipping restart.")
        }
    }

    companion object {
        private const val TAG = "GreenGainsBootReceiver"
        private const val PREFS_NAME = "FlutterSharedPreferences"
        private const val PREF_FOREGROUND_ENABLED = "flutter.foreground_service_enabled"
    }
}
