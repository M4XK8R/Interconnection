package com.maxkor.interonnection.ui.screens.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.maxkor.interonnection.ui.SharedViewModel
import com.maxkor.interonnection.ui.screens.DataCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(viewModel: SharedViewModel) {

//    val dataList = remember { viewModel.dataLIst }
    val dataList = viewModel.dataListReactive.collectAsState(initial = emptyList())

    LazyColumn() {
        items(dataList.value.filter { it.isFavorite }, { it.id }) { dataModel ->
            val textFieldText = remember { mutableStateOf("") }

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
//                        textFieldTextState = textFieldText,
                        viewModel = viewModel
                    )
                }
            )
        }
    }
}