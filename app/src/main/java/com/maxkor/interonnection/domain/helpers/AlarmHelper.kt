package com.maxkor.interonnection.domain.helpers

interface AlarmHelper {

    fun createAlarm(time: Long, extraText: String, itemId: String)
}