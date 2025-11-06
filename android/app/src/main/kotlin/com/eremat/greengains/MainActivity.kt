package com.eremat.greengains

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    private val channelName = "greengains/power"
    private val fgChannelName = "greengains/foreground"
    private val notifChannelName = "greengains/notifications"

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // Handle notification tap when app is already running in background
        // This prevents creating a new instance (already handled by singleTop)
        // Just bring the existing activity to foreground
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channelName).setMethodCallHandler { call, result ->
            when (call.method) {
                "isIgnoringBatteryOptimizations" -> {
                    result.success(isIgnoringBatteryOptimizations())
                }
                "requestIgnoreBatteryOptimizations" -> {
                    val ok = requestIgnoreBatteryOptimizations()
                    result.success(ok)
                }
                else -> result.notImplemented()
            }
        }

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, fgChannelName).setMethodCallHandler { call, result ->
            when (call.method) {
                "startForegroundService" -> {
                    val ok = startFgService()
                    result.success(ok)
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

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, notifChannelName).setMethodCallHandler { call, result ->
            when (call.method) {
                "areNotificationsEnabled" -> {
                    result.success(areNotificationsEnabled())
                }
                "openNotificationSettings" -> {
                    val ok = openNotificationSettings()
                    result.success(ok)
                }
                else -> result.notImplemented()
            }
        }
    }

    private fun isIgnoringBatteryOptimizations(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true
        val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
        return pm.isIgnoringBatteryOptimizations(packageName)
    }

    private fun requestIgnoreBatteryOptimizations(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true
        return try {
            val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS).apply {
                data = Uri.parse("package:$packageName")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
            true
        } catch (_: Exception) {
            // Fallback to the general Ignore Battery Optimization settings page
            return try {
                val fallback = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(fallback)
                true
            } catch (_: Exception) {
                false
            }
        }
    }

    private fun startFgService(): Boolean {
        return try {
            val intent = Intent(this, ForegroundService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            } else {
                startService(intent)
            }
            true
        } catch (_: Exception) {
            false
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

    private fun areNotificationsEnabled(): Boolean {
        val nm = androidx.core.app.NotificationManagerCompat.from(this)
        return nm.areNotificationsEnabled()
    }

    private fun openNotificationSettings(): Boolean {
        return try {
            val intent = Intent().apply {
                action = android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS
                putExtra(android.provider.Settings.EXTRA_APP_PACKAGE, packageName)
                putExtra("app_package", packageName)
                putExtra("app_uid", applicationInfo.uid)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
            true
        } catch (_: Exception) {
            return try {
                val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.parse("package:$packageName")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(intent)
                true
            } catch (_: Exception) {
                false
            }
        }
    }
}
