package com.maxkor.interonnection.domain.helpers

import android.content.Context

interface ImageShareHelper {

    fun shareImageFromUrl(imageUrl: String, context: Context)
}