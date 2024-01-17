package com.maxkor.interonnection.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.maxkor.interonnection.R
import com.maxkor.interonnection.ui.screens.DataModel

@Composable
fun DetailScreen(dataModel: DataModel = DataModel.testModel) {

    var openDialog = remember { mutableStateOf(false) }

    if (!openDialog.value) {
        Box(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = dataModel.mainText,
                    fontSize = TextUnit(22f, TextUnitType.Sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Spacer(modifier = Modifier.size(15.dp))

                Image(
                    painter = painterResource(id = R.drawable.test),
                    contentDescription = "test image",
                    modifier = Modifier.size(256.dp),
                    contentScale = ContentScale.FillBounds,
                )

                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    text = stringResource(id = R.string.test_long_text),
                    fontSize = TextUnit(22f, TextUnitType.Sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
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
        ReminderDialog(openDialog)
    }
}

@Composable
@Preview(showSystemUi = true)
fun DetailScreenPreview() {
    DetailScreen(DataModel.testModel)
}