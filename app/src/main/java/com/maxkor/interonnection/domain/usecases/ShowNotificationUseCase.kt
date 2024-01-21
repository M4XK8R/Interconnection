package com.maxkor.interonnection.domain.usecases

import android.app.PendingIntent
import com.maxkor.interonnection.domain.helpers.NotificationHelper
import javax.inject.Inject

class ShowNotificationUseCase @Inject constructor(
    private val notificationHelper: NotificationHelper
) {
    operator fun invoke(contentText: String?, contentIntent: PendingIntent?) {
        notificationHelper.showNotification(contentText, contentIntent)
    }
}