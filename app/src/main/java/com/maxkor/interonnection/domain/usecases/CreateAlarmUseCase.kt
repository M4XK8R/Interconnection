package com.maxkor.interonnection.domain.usecases

import com.maxkor.interonnection.domain.helpers.AlarmHelper
import javax.inject.Inject

class CreateAlarmUseCase @Inject constructor(
    private val alarmHelper: AlarmHelper
) {
    operator fun invoke(time: Long, extraText: String, itemId: String) {
        alarmHelper.createAlarm(time, extraText, itemId)
    }
}