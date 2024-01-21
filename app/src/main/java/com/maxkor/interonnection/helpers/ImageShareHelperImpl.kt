package com.maxkor.interonnection.helpers

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.maxkor.interonnection.domain.helpers.ImageShareHelper
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ImageShareHelperImpl @Inject constructor() : ImageShareHelper {

    override fun shareImageFromUrl(imageUrl: String, context: Context) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"

        val uri: Uri = Uri.parse(imageUrl)
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)

        context.startActivity(Intent.createChooser(shareIntent, "Share image"))
    }
}