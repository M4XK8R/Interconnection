package com.maxkor.interonnection.ui.screens.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDelete(
    dismissState: DismissState,
    dismissContent: @Composable () -> Unit,
) {
    SwipeToDismiss(
        state = dismissState,
        background = {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            )
        },
        dismissContent = { dismissContent() }
    )
}