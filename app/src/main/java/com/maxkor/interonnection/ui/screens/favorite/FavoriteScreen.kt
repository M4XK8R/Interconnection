package com.maxkor.interonnection.ui.screens.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkor.interonnection.ui.screens.composables.SwipeToDelete
import com.maxkor.interonnection.ui.screens.composables.card.DataCard
import com.maxkor.interonnection.ui.screens.composables.card.DataCardState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val dataList = viewModel.dataListReactive.collectAsState(initial = emptyList())

    LazyColumn() {
        items(dataList.value.filter { it.isFavorite }, { it.id }) { dataModel ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.StartToEnd) or
                dismissState.isDismissed(DismissDirection.EndToStart)
            ) {
                viewModel.removeFromFavorites(dataModel)
            }
            SwipeToDelete(
                dismissState = dismissState,
                dismissContent = {
                    DataCard(
                        dataModel = dataModel,
                        addToFavorites = { viewModel.addToFavorites(it) },
                        removeFromFavorites = { viewModel.removeFromFavorites(it) },
                        addDescription = { dataModel, text ->
                            viewModel.addDescription(dataModel, text)
                        },
                        dataCardState = DataCardState.Favorites
                    )
                }
            )
        }
    }
}