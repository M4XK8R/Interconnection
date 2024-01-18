package com.maxkor.interonnection.ui.screens.detail

import android.content.Context
import android.media.MediaScannerConnection
import android.os.Environment
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.net.URL

object PicturesSaver {

    fun saveImageToGallery(
        context: Context,
        imageUrl: String,
        coroutineScope: CoroutineScope,
        fileName: String
    ) {
        val directory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)

        // Check if the directory exists, if not create it
        if (!directory.exists()) {
            directory.mkdirs()
        }

        // Create a new file in the Pictures directory with the specified file name
        val file = File(directory, fileName)

        // Use kotlin coroutines to download the image from the url and save it to the file
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val inputStream = URL(imageUrl).openStream()
                val outputStream = FileOutputStream(file)
                inputStream.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }
                outputStream.close()

                // Use MediaScanner to notify the system about the new image
                MediaScannerConnection.scanFile(
                    context,
                    arrayOf(file.path),
                    arrayOf("image/jpeg"),
                    null
                )
                coroutineScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                coroutineScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
                e.printStackTrace()
            }
        }
    }

}