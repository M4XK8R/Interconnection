package com.maxkor.interonnection.ui.screens.favorite

import androidx.activity.ComponentActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maxkor.interonnection.ui.SharedViewModel
import com.maxkor.interonnection.ui.screens.DataCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(viewModel: SharedViewModel) {

//    val viewModel: SharedViewModel = viewModel(LocalContext.current as ComponentActivity)

    val dataList = remember {
        mutableStateOf(viewModel.dataLIst.value.filter { it.isFavorite })
    }

    LazyColumn() {
        items(dataList.value, { it.id }) { dataModel ->
            val textFieldText = remember { mutableStateOf("") }

            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.StartToEnd) or
                dismissState.isDismissed(DismissDirection.EndToStart)
            ) {
                viewModel.removeItem(dataModel)
            }
            SwipeToDelete(
                dismissState = dismissState,
                dismissContent = {
                    DataCard(
                        dataModel = dataModel,
                        textFieldTextState = textFieldText,
                        viewModel = viewModel
                    )
                }
            )
        }
    }
}