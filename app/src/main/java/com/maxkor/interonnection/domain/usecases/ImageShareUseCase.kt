package com.maxkor.interonnection.domain.usecases

import android.content.Context
import com.maxkor.interonnection.domain.helpers.ImageShareHelper
import javax.inject.Inject

class ImageShareUseCase @Inject constructor(
    private val imageShareHelper: ImageShareHelper
) {

    operator fun invoke(imageUrl: String, context: Context) {
        imageShareHelper.shareImageFromUrl(imageUrl, context)
    }
}