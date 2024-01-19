package com.maxkor.interonnection.helpers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.maxkor.interonnection.broadcast.NotyReceiver
import java.util.Calendar


object AlarmHelper {

    const val EXTRA_TEXT_KEY = "text_key"

    fun createAlarm(context: Context, time: Long, extraText: String) {
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