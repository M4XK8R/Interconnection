package com.maxkor.interonnection.ui.screens.base

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkor.interonnection.navigation.MainNavGraph
import com.maxkor.interonnection.navigation.NavigationHelper
import com.maxkor.interonnection.ui.screens.bar.MyBottomBar
import com.maxkor.interonnection.ui.screens.detail.DetailScreen
import com.maxkor.interonnection.ui.screens.favorite.FavoriteScreen
import com.maxkor.interonnection.ui.screens.list.ListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    val navHelper = NavigationHelper.rememberNavigationState()

    val snackbarHostState = remember { viewModel.snackbarHostState }

    Scaffold(
        bottomBar = { MyBottomBar(navHelper) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) {
        MainNavGraph(
            navHostController = navHelper.navHostController,
            listScreenContent = { ListScreen(navHelper) },
            detailScreenContent = { dataModelId ->
                DetailScreen(dataModelId)
            },
            favoriteScreenContent = { FavoriteScreen() }
        )
    }
}