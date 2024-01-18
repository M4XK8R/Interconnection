package com.maxkor.interonnection.ui.screens.detail

import android.content.Context
import android.content.Intent
import android.net.Uri

object ImageShareHelper {

    fun shareImageFromUrl(context: Context, imageUrl: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"

        val uri: Uri = Uri.parse(imageUrl)
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)

        context.startActivity(Intent.createChooser(shareIntent, "Share image"))
    }
}