package com.eremat.greengains.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.util.Log

/**
 * Monitors battery state and provides information about battery level and charging status.
 * Used to implement battery-friendly behavior (pause uploads when battery is low).
 */
class BatteryStateMonitor(private val context: Context) {
    companion object {
        private const val TAG = "BatteryStateMonitor"
        private const val LOW_BATTERY_THRESHOLD = 15 // Pause when battery < 15%
    }

    private var isRegistered = false
    private var currentBatteryLevel = 100
    private var isCharging = false

    private val batteryReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                Intent.ACTION_BATTERY_CHANGED -> {
                    val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                    val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                    currentBatteryLevel = if (level >= 0 && scale > 0) {
                        (level * 100 / scale)
                    } else {
                        100 // Default to full battery if we can't read it
                    }

                    val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                    isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                                status == BatteryManager.BATTERY_STATUS_FULL

                    Log.d(TAG, "Battery: ${currentBatteryLevel}%, Charging: $isCharging")
                }
                Intent.ACTION_BATTERY_LOW -> {
                    Log.w(TAG, "Battery low warning received")
                }
                Intent.ACTION_BATTERY_OKAY -> {
                    Log.i(TAG, "Battery okay again")
                }
            }
        }
    }

    /**
     * Start monitoring battery state.
     */
    fun startMonitoring() {
        if (isRegistered) {
            Log.w(TAG, "Battery monitor already registered")
            return
        }

        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_OKAY)
        }

        context.registerReceiver(batteryReceiver, filter)
        isRegistered = true
        Log.i(TAG, "Battery monitoring started")
    }

    /**
     * Stop monitoring battery state.
     */
    fun stopMonitoring() {
        if (!isRegistered) {
            return
        }

        try {
            context.unregisterReceiver(batteryReceiver)
            isRegistered = false
            Log.i(TAG, "Battery monitoring stopped")
        } catch (e: IllegalArgumentException) {
            Log.w(TAG, "Battery receiver was not registered", e)
        }
    }

    /**
     * Check if uploads should be paused due to low battery.
     * Returns true if battery is low AND not charging.
     */
    fun shouldPauseForBattery(): Boolean {
        val shouldPause = currentBatteryLevel < LOW_BATTERY_THRESHOLD && !isCharging
        if (shouldPause) {
            Log.i(TAG, "Pausing uploads: battery ${currentBatteryLevel}%, charging: $isCharging")
        }
        return shouldPause
    }

    /**
     * Get current battery level (0-100).
     */
    fun getBatteryLevel(): Int = currentBatteryLevel

    /**
     * Check if device is currently charging.
     */
    fun isCharging(): Boolean = isCharging
}
