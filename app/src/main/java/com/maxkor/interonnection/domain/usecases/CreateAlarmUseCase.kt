package com.maxkor.interonnection.domain.usecases

import android.content.Context
import com.maxkor.interonnection.domain.helpers.AlarmHelper
import javax.inject.Inject

class CreateAlarmUseCase @Inject constructor(
    private val alarmHelper: AlarmHelper
) {
    operator fun invoke(time: Long, extraText: String) {
        alarmHelper.createAlarm(time, extraText)
    }
}