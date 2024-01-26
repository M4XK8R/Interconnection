package com.maxkor.interonnection.ui.screens.base

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkor.interonnection.broadcast.NotyReceiver
import com.maxkor.interonnection.navigation.MainNavGraph
import com.maxkor.interonnection.navigation.NavigationHelper
import com.maxkor.interonnection.ui.screens.composables.bar.MyBottomBar
import com.maxkor.interonnection.ui.screens.detail.DetailScreen
import com.maxkor.interonnection.ui.screens.favorite.FavoriteScreen
import com.maxkor.interonnection.ui.screens.list.ListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    itemId: String,
    viewModel: MainViewModel = hiltViewModel()
) {
    val navHelper = NavigationHelper.rememberNavigationState()

    val snackbarHostState = remember { viewModel.snackbarHostState }
    val itemIdState = remember { mutableStateOf(itemId) }

    Scaffold(
        bottomBar = { MyBottomBar(navHelper) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) {
        MainNavGraph(
            navHostController = navHelper.navHostController,
            listScreenContent = { ListScreen(navHelper, itemIdState) },
            detailScreenContent = { dataModelId ->
                DetailScreen(
                    dataModelId = dataModelId,
                    navigationHelper = navHelper
                )
            },
            favoriteScreenContent = { FavoriteScreen() }
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun MainPreview() {
    MainScreen(NotyReceiver.ID_DEFAULT_VALUE)
}