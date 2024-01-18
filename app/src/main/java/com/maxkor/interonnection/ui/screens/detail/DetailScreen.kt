package com.maxkor.interonnection.ui.screens.detail

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.ui.SharedViewModel
import com.maxkor.interonnection.ui.screens.detail.ImageShareHelper.shareImageFromUrl
import kotlinx.coroutines.Dispatchers

@Composable
fun DetailScreen(viewModel: SharedViewModel) {

    val openDialog = remember { mutableStateOf(false) }
    val element = viewModel.currentElement.value

    val context = LocalContext.current

    if (!openDialog.value) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(bottom = 86.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = element.fullName,
                    fontSize = TextUnit(22f, TextUnitType.Sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Spacer(modifier = Modifier.size(15.dp))

                AsyncImage(
                    model = element.imageUrl,
                    contentDescription = "Actor",
                    modifier = Modifier
                        .size(256.dp),
                    contentScale = ContentScale.FillBounds,
                )

                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    text = element.extraText,
                    fontSize = TextUnit(22f, TextUnitType.Sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.size(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    val coroutine = rememberCoroutineScope()
                    Button(onClick = {
                        createLog("uri = ${element.imageUrl.toUri()}")
                        PicturesSaver.saveImageToGallery(
                            context,
                            element.imageUrl,
                            coroutine,
                            "${element.fullName}.jpg"
                        )
                    }) {
                        Text(text = "Save ")
                    }

                    Button(onClick = {
                        shareImageFromUrl(context, element.imageUrl)
                    }) {
                        Text(text = "Share")
                    }
                }
            }

            Button(
                onClick = { openDialog.value = true },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Text(text = "Create a reminder")
            }
        }
    }
    if (openDialog.value) {
        ReminderDialog(openDialog, element.fullName)
    }
}

//@Composable
//@Preview(showSystemUi = true)
//fun DetailScreenPreview() {
//    DetailScreen(DataModelTest.testModel)
//}