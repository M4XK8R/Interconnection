package com.maxkor.interonnection.domain.helpers

import android.content.Context

interface PicturesSaver {

    fun saveImageToDownloadsFolder(
        context: Context,
        imageUrl: String,
        fileName: String,
    )

    fun saveImageToPicturesFolder(
        context: Context,
        downloadUrlOfImage: String,
        filename: String
    )
}