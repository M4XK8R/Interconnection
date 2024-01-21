package com.maxkor.interonnection.broadcast

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.maxkor.interonnection.MainActivity
import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.domain.usecases.ShowNotificationUseCase
import com.maxkor.interonnection.helpers.AlarmHelperImpl
import com.maxkor.interonnection.helpers.NotificationHelperImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val CONTENT_TEXT_DEFAULT = "Unknown"

@AndroidEntryPoint
class NotyReceiver : BroadcastReceiver() {

    @Inject
    lateinit var showNotificationUseCase: ShowNotificationUseCase

    override fun onReceive(context: Context, intent: Intent) {
        createLog("onReceive")
        val extraText =
            intent.getStringExtra(AlarmHelperImpl.EXTRA_TEXT_KEY) ?: CONTENT_TEXT_DEFAULT
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

        showNotificationUseCase(extraText, contentIntent)
    }
}