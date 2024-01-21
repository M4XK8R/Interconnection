package com.maxkor.interonnection.domain.usecases

import android.content.Context
import com.maxkor.interonnection.domain.helpers.PicturesSaver
import javax.inject.Inject

class SavePictureUseCase @Inject constructor(
    private val picturesSaver: PicturesSaver
) {

    operator fun invoke(
        context: Context,
        downloadUrlOfImage: String,
        filename: String
    ) {
        picturesSaver.saveImageToPicturesFolder(context, downloadUrlOfImage, filename)
    }
}