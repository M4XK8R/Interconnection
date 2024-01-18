package com.maxkor.interonnection.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.maxkor.interonnection.R
import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.data.retrofit.DataModel
import com.maxkor.interonnection.ui.SharedViewModel

private const val MAX_MAIN_TEXT_LINES = 1
private const val MAX_SECOND_TEXT_LINES = 2
private const val EMPTY_TEXT = ""

@Composable
fun DataCard(
    dataModel: DataModel,
    textFieldTextState: MutableState<String>,
    viewModel: SharedViewModel,
    modifier: Modifier = Modifier,
) {
    var isFavorite by remember { mutableStateOf(dataModel.isFavorite) }
    var extraText by remember { mutableStateOf(dataModel.extraText) }
    val modeState = remember { mutableStateOf(CardState.defaultMode) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
//            .height(88.dp)
//            .background(Color.LightGray)
            .padding(start = 3.dp, end = 3.dp, top = 3.dp, bottom = 3.dp)
            .then(modifier)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
//                .background(Color.Red)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
//                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
//                    .background(Color.Green),
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    model = dataModel.imageUrl,
                    contentDescription = "Actor",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                )

                Spacer(modifier = Modifier.size(16.dp))

                Column(
                    Modifier
//                        .height(64.dp),
                        .fillMaxHeight()
//                    .width(250.dp)
//                        .background(Color.LightGray),
                    ,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = dataModel.fullName ?: "",
//                      modifier = Modifier.requiredWidthIn(100.dp, 150.dp),
                        fontSize = TextUnit(18f, TextUnitType.Sp),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = FontFamily.SansSerif,
                        maxLines = MAX_MAIN_TEXT_LINES,
                        softWrap = false,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (isFavorite) {
                        when (modeState.value) {
                            CardState.ModeRead -> {
                                if (extraText.isNotEmpty()) {
                                    FavoriteModeText(
                                        text = extraText,
                                        changeCardMode = {
                                            modeState.value = CardState.ModeEdit
                                        }
                                    )
                                } else {
                                    FavoriteModeText(
                                        text = "Add description",
                                        changeCardMode = {
                                            modeState.value = CardState.ModeEdit
                                        }
                                    )
                                }
                            }

                            CardState.ModeEdit -> {
                                Row {
//                                    var textFieldText by remember { mutableStateOf("") }
                                    TextField(
                                        value = textFieldTextState.value,
                                        onValueChange = {
                                            textFieldTextState.value = it
                                            extraText = it
                                        },
                                        modifier = Modifier.width(170.dp),
                                        textStyle = MaterialTheme.typography.bodySmall,
                                        placeholder = {
                                            Text(
                                                text = "Add description",
                                                fontSize = TextUnit(12f, TextUnitType.Sp),
                                                color = MaterialTheme.colorScheme.onBackground,
                                                style = MaterialTheme.typography.displaySmall,
                                                fontFamily = FontFamily.Monospace
                                            )
                                        },
                                    )

                                    Spacer(modifier = Modifier.size(10.dp))

                                    IconButton(
                                        onClick = {
                                            val newList = viewModel.dataLIst.value.toMutableList()
                                            val index = newList.indexOf(dataModel)
                                            newList[index] = dataModel.copy(extraText = extraText)
                                            viewModel.updateData(newList)
                                            modeState.value = CardState.ModeRead
                                        }
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.baseline_add_task_24),
                                            contentDescription = "Confirm"
                                        )
                                    }
                                }

                            }
                        }
                    }
                }
            }

            // Fav image section
            Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                if (isFavorite) {
                    Image(
                        painter = painterResource(id = R.drawable.iv_picked),
                        contentDescription = "Favorite image",
                        Modifier
                            .size(32.dp)
                            .clickable {
                                val newList = viewModel.dataLIst.value.toMutableList()
                                val index = newList.indexOf(dataModel)
                                newList[index] = dataModel.copy(
                                    extraText = EMPTY_TEXT,
                                    isFavorite = false
                                )
                                viewModel.updateData(newList)
                                extraText = EMPTY_TEXT
                                textFieldTextState.value = EMPTY_TEXT
                                isFavorite = false
                            }
                    )
                }
                if (!isFavorite) {
                    Image(
                        painter = painterResource(id = R.drawable.iv_unpicked),
                        contentDescription = "Favorite image",
                        Modifier
                            .size(32.dp)
                            .clickable {
                                val newList = viewModel.dataLIst.value.toMutableList()
                                val index = newList.indexOf(dataModel)
                                newList[index] = dataModel.copy(
                                    extraText = extraText,
                                    isFavorite = true
                                )
                                viewModel.updateData(newList)
                                isFavorite = true
                            }
                    )
                }
            }
        }
    }
}

private sealed class CardState() {
    data object ModeRead : CardState()
    data object ModeEdit : CardState()

    companion object {
        val defaultMode: CardState = ModeRead
    }
}

@Composable
private fun FavoriteModeText(
    text: String,
    changeCardMode: () -> Unit,
) {
    Text(
        text = text,
        modifier = Modifier
            .requiredWidthIn(100.dp, 150.dp)
            .clickable { changeCardMode() },
        fontSize = TextUnit(12f, TextUnitType.Sp),
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.labelSmall,
        fontFamily = FontFamily.Monospace,
        maxLines = MAX_SECOND_TEXT_LINES,
        softWrap = true,
        overflow = TextOverflow.Ellipsis
    )
}
