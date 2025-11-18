package com.eremat.greengains.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.format.DateUtils
import androidx.core.app.NotificationCompat
import com.eremat.greengains.MainActivity
import com.eremat.greengains.R
import com.eremat.greengains.service.ForegroundService
import java.time.Instant

internal object NotificationsHelper {

    private const val NOTIFICATION_CHANNEL_ID = "general_notification_channel"
    private const val PREFS_NAME = "FlutterSharedPreferences"
    private const val PREF_LAST_UPLOAD = "flutter.last_upload_at"
    const val NOTIFICATION_ID_SERVICE = 1
    const val NOTIFICATION_ID_WORKER = 2

    fun createNotificationChannel(context: Context) {
        val notificationManager = context.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "Location Tracking",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }

    fun buildNotification(context: Context, lastUploadMillis: Long? = null): Notification {
        val stopIntent = Intent(context, ForegroundService::class.java).apply {
            action = ForegroundService.ACTION_STOP_SERVICE
        }
        val stopPendingIntent = PendingIntent.getService(
            context,
            0,
            stopIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val (subtitle, expandedText) = buildSubtitle(context, lastUploadMillis)

        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(context.getString(R.string.app_name))
            .setContentText(subtitle)
            .setStyle(NotificationCompat.BigTextStyle().bigText(expandedText))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setColor(Color.parseColor("#4CAF50"))
            .setOngoing(true)
            .addAction(
                R.mipmap.ic_launcher,
                context.getString(R.string.notification_action_stop, context.getString(R.string.app_name)),
                stopPendingIntent
            )
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .setContentIntent(Intent(context, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
            })
            .build()
    }

    private fun buildSubtitle(context: Context, lastUploadMillis: Long?): Pair<String, String> {
        val status = context.getString(R.string.notification_status_collecting)
        val lastUploadText = lastUploadMillis?.let {
            val relative = DateUtils.getRelativeTimeSpanString(
                it,
                System.currentTimeMillis(),
                DateUtils.MINUTE_IN_MILLIS
            ).toString()
            context.getString(R.string.notification_last_upload, relative)
        } ?: context.getString(R.string.notification_last_upload_unknown)

        return status to "$status\n$lastUploadText"
    }

    fun readLastUploadFromPrefs(context: Context): Long? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val raw = prefs.getString(PREF_LAST_UPLOAD, null) ?: return null
        return runCatching { Instant.parse(raw).toEpochMilli() }.getOrNull()
    }

    fun notifyUpdate(context: Context, manager: NotificationManager, lastUpload: Long?) {
        manager.notify(NOTIFICATION_ID_SERVICE, buildNotification(context, lastUpload))
    }

    fun buildWorkerNotification(context: Context): Notification {
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("GreenGains processing data")
            .setContentText("Crunching recent sensor batches")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setColor(Color.parseColor("#4CAF50"))
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .setContentIntent(Intent(context, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
            })
            .build()
    }
}
