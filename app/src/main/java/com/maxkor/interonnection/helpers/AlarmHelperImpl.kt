package com.maxkor.interonnection.helpers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.maxkor.interonnection.broadcast.NotyReceiver
import com.maxkor.interonnection.domain.helpers.AlarmHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import javax.inject.Inject

class AlarmHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AlarmHelper {

    companion object {
        const val EXTRA_TEXT_KEY = "text_key"
    }

    override fun createAlarm(time: Long, extraText: String) {
        val alarmIntent = Intent(context, NotyReceiver::class.java).let { intent ->
            intent.putExtra(EXTRA_TEXT_KEY, extraText)
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        }
        val currentTime = Calendar.getInstance().timeInMillis
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(
            AlarmManager.RTC,
            currentTime + time,
            alarmIntent
        )
    }
}