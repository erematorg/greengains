package com.eremat.greengains

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
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

        private val SENSOR_TRIGGER_INTERVAL = 10.seconds
    }

    private val coroutineScope = CoroutineScope(Job())
    private var tickerJob: Job? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: Service created")
        createChannel()
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
        tickerJob?.cancel()
        coroutineScope.coroutineContext.cancelChildren()
        super.onDestroy()
        Log.d(TAG, "onDestroy: Service destroyed")
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun startPeriodicSensorTrigger() {
        Log.d(TAG, "startPeriodicSensorTrigger: Starting coroutine ticker")
        tickerJob = coroutineScope.launch {
            tickerFlow(SENSOR_TRIGGER_INTERVAL, SENSOR_TRIGGER_INTERVAL)
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

        return NotificationCompat.Builder(this@ForegroundService, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_compass)
            .setContentTitle("GreenGains is collecting data")
            .setContentText("Last upload just moments ago")
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setShowWhen(false)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }
}
