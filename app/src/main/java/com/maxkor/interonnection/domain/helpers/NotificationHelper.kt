package com.maxkor.interonnection.domain.helpers

import android.app.PendingIntent

interface NotificationHelper {

    fun showNotification(contentText: String?, contentIntent: PendingIntent?)
}