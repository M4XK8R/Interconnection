package com.maxkor.interonnection

import android.util.Log

private const val TEST_TAG = "global_test"

fun createLog(msg: String) {
    Log.d(TEST_TAG, msg)
}

fun createLog(msg: String, tag: String) {
    Log.d(tag, msg)
}