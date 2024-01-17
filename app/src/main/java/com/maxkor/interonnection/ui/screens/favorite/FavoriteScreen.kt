package com.maxkor.interonnection.ui.screens.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import com.maxkor.interonnection.ui.screens.DataModel
import com.maxkor.interonnection.ui.screens.DataCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen() {

    LazyColumn() {
        items(DataModel.testList) {
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.StartToEnd) or
                dismissState.isDismissed(DismissDirection.EndToStart)
            ) {
//        viewModel.removeItem(item)
            }
            SwipeToDelete(
                dismissState = dismissState,
                dismissContent = { DataCard(dataModel = it) }
            )
        }
    }
}