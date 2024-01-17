package com.maxkor.interonnection.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.maxkor.interonnection.ui.screens.list.ListScreen
import com.maxkor.interonnection.ui.screens.detail.DetailScreen

@Composable
fun BaseScreen() {
    val initialState: ScreenState = ScreenState.ListState
    val screenState = remember {
        mutableStateOf(initialState)
    }

    val currentDataModel = remember {
        mutableStateOf(DataModel.testModel)
    }

    when (screenState.value) {
        ScreenState.ListState -> {
            ListScreen(
                screenState = screenState,
                dataModel = currentDataModel
            )
        }

        ScreenState.DetailState -> {
            DetailScreen(currentDataModel.value)
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun BaseScreenPreview() {
    BaseScreen()
}