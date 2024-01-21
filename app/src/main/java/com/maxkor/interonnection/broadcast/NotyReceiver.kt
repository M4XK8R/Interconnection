package com.maxkor.interonnection.broadcast

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.maxkor.interonnection.MainActivity
import com.maxkor.interonnection.domain.usecases.ShowNotificationUseCase
import com.maxkor.interonnection.helpers.AlarmHelperImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val UNKNOWN_VALUE = "Unknown"

@AndroidEntryPoint
class NotyReceiver : BroadcastReceiver() {

    @Inject
    lateinit var showNotificationUseCase: ShowNotificationUseCase

    companion object {
        const val ACTION_SHOW_REQUIRED_SCREEN = "show_detail_screen"
        const val ID_PARAM = "data_model_id"
        const val ID_DEFAULT_VALUE = "-1"

    }

    override fun onReceive(context: Context, intent: Intent) {
        val extraText = intent.getStringExtra(AlarmHelperImpl.EXTRA_TEXT_KEY)
            ?: UNKNOWN_VALUE

        val itemId = intent.getStringExtra(AlarmHelperImpl.ITEM_ID_KEY)
            ?: UNKNOWN_VALUE

        val newIntent = Intent(context, MainActivity::class.java).apply {
            action = ACTION_SHOW_REQUIRED_SCREEN
            putExtra(ID_PARAM, itemId)
        }
        val contentIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            newIntent,
            FLAG_IMMUTABLE or FLAG_UPDATE_CURRENT
        )

        showNotificationUseCase(extraText, contentIntent)
    }
}