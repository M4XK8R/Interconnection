package com.maxkor.interonnection.ui.screens.composables.card

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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.maxkor.interonnection.R
import com.maxkor.interonnection.domain.models.DataModel
import kotlin.math.roundToInt

private const val MAX_MAIN_TEXT_LINES = 1
private const val MAX_SECOND_TEXT_LINES = 2

@Composable
fun DataCard(
    dataModel: DataModel,
    addToFavorites: (DataModel) -> Unit,
    removeFromFavorites: (DataModel) -> Unit,
    addDescription: (DataModel, String) -> Unit,
    modifier: Modifier = Modifier,
    dataCardState: DataCardState = DataCardState.List,
) {
    val cardModeState = remember { mutableStateOf(CardMode.defaultMode) }
    val cardState = remember { mutableStateOf(dataCardState) }
    var textFieldState by remember { mutableStateOf(dataModel.extraText) }
    var openDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, end = 3.dp, top = 3.dp, bottom = 3.dp)
            .then(modifier)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = dataModel.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                )

                Spacer(modifier = Modifier.size(16.dp))

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    val price = dataModel.price.toDouble()
                    val priceRound = (price * 100.0).roundToInt() / 100.0
                    Text(
                        text = "${dataModel.fullName} $priceRound",
//                      modifier = Modifier.requiredWidthIn(100.dp, 150.dp),
                        fontSize = TextUnit(18f, TextUnitType.Sp),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = FontFamily.SansSerif,
                        maxLines = MAX_MAIN_TEXT_LINES,
                        softWrap = false,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (dataModel.isFavorite) {
                        when (cardModeState.value) {
                            CardMode.Read -> {
                                if (textFieldState.isNotEmpty()) {
                                    FavoriteModeText(
                                        text = textFieldState,
                                        changeCardMode = {
                                            cardModeState.value = CardMode.Edit
                                        }
                                    )
                                } else {
                                    FavoriteModeText(
                                        text = "Add description",
                                        changeCardMode = {
                                            cardModeState.value = CardMode.Edit
                                        }
                                    )
                                }
                            }

                            CardMode.Edit -> {
                                Row {
                                    TextField(
                                        value = textFieldState,
                                        onValueChange = { textFieldState = it },
                                        modifier = Modifier.width(150.dp),
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
                                            addDescription.invoke(dataModel, textFieldState)
                                            cardModeState.value = CardMode.Read
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
                if (dataModel.isFavorite) {
                    FavoriteImage(
                        dataModel = dataModel,
                        painterResourceId = R.drawable.iv_picked,
                        changeFavoriteState = { dataModel ->
                            if (cardState.value == DataCardState.List) {
                                removeFromFavorites.invoke(dataModel)
                                textFieldState = DataModel.DEFAULT_EXTRA_TEXT
                            }
                            if (cardState.value == DataCardState.Favorites) {
                                openDialog = true
                            }
                        }
                    )
                }

                if (openDialog) {
                    AlertDialog(
                        onDismissRequest = { openDialog = false },
                        confirmButton = {
                            Row(modifier.clickable {
                                removeFromFavorites.invoke(dataModel)
                                textFieldState = DataModel.DEFAULT_EXTRA_TEXT
                            }) {
                                Text(
                                    text = "OK",
                                    fontSize = TextUnit(18f, TextUnitType.Sp),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontFamily = FontFamily.SansSerif,
                                )
                            }
                        },
                        text = {
                            Text(
                                text = "Remove from favorites?",
                                fontSize = TextUnit(18f, TextUnitType.Sp),
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.titleLarge,
                                fontFamily = FontFamily.SansSerif,
                            )
                        }
                    )
                }

                if (!dataModel.isFavorite) {
                    FavoriteImage(
                        dataModel = dataModel,
                        R.drawable.iv_unpicked,
                        changeFavoriteState = { dataModel ->
                            addToFavorites.invoke(dataModel)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun FavoriteImage(
    dataModel: DataModel,
    painterResourceId: Int,
    changeFavoriteState: (DataModel) -> Unit
) {
    Image(
        painter = painterResource(id = painterResourceId),
        contentDescription = "Favorite image",
        Modifier
            .size(32.dp)
            .clickable { changeFavoriteState.invoke(dataModel) }
    )
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

private sealed class CardMode() {
    data object Read : CardMode()
    data object Edit : CardMode()

    companion object {
        val defaultMode: CardMode = Read
    }
}
