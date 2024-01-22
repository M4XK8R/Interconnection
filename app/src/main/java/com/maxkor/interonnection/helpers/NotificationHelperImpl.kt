package com.maxkor.interonnection.helpers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import androidx.core.app.NotificationCompat
import com.maxkor.interonnection.R
import com.maxkor.interonnection.domain.helpers.NotificationHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val CHANNEL_ID = "channel_id"
private const val NOTIFICATION_ID = 79416

class NotificationHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : NotificationHelper {

    override fun showNotification(contentText: String?, contentIntent: PendingIntent?) {
        val notyManager = context.getSystemService(
            Service.NOTIFICATION_SERVICE
        ) as NotificationManager

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_circle_notifications_24)
            .setContentText(contentText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .build()

        val channel = NotificationChannel(
            CHANNEL_ID,
            "channel name",
            NotificationManager.IMPORTANCE_DEFAULT
        ).also { it.description = "channel description" }

        notyManager.createNotificationChannel(channel)
        notyManager.notify(NOTIFICATION_ID, notification)
    }
}
