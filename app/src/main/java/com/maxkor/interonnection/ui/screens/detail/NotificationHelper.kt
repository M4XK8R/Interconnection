package com.maxkor.interonnection.ui.screens.detail

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import androidx.core.app.NotificationCompat
import com.maxkor.interonnection.R

private const val CHANNEL_ID = "channel_id"
private const val NOTIFICATION_ID = 79416

object NotificationHelper {

    fun showNotification(context: Context) {
        val notyManager = context.getSystemService(
            Service.NOTIFICATION_SERVICE
        ) as NotificationManager

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.iv_picked)
            .setContentText("Test text")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
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
