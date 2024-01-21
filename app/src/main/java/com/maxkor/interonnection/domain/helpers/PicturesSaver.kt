package com.maxkor.interonnection.domain.helpers

import android.content.Context

interface PicturesSaver {

    fun saveImageToPicturesFolder(
        context: Context,
        downloadUrlOfImage: String,
        filename: String
    )
}