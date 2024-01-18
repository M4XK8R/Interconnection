package com.maxkor.interonnection.ui.screens.detail

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.maxkor.interonnection.MainActivity
import com.maxkor.interonnection.createLog

private const val CONTENT_TEXT_DEFAULT = "Unknown"

class NotyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        createLog("onReceive")
        val extraText = intent.getStringExtra(AlarmHelper.EXTRA_TEXT_KEY) ?: CONTENT_TEXT_DEFAULT
        createLog("extraText = $extraText")

        val newIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val contentIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            newIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        NotificationHelper.showNotification(context, extraText, contentIntent)
    }
}