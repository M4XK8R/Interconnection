package com.maxkor.interonnection.domain.helpers

import android.content.Context

interface AlarmHelper {

    fun createAlarm(time: Long, extraText: String)
}