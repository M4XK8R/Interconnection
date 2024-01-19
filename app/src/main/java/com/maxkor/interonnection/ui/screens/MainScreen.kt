package com.maxkor.interonnection.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkor.interonnection.navigation.MainNavGraph
import com.maxkor.interonnection.navigation.NavigationHelper
import com.maxkor.interonnection.ui.SharedViewModel
import com.maxkor.interonnection.ui.screens.bar.MyBottomBar
import com.maxkor.interonnection.ui.screens.detail.DetailScreen
import com.maxkor.interonnection.ui.screens.favorite.FavoriteScreen
import com.maxkor.interonnection.ui.screens.list.ListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val viewModel: SharedViewModel = hiltViewModel()

    val navHelper = NavigationHelper.rememberNavigationState()

    Scaffold(
        bottomBar = { MyBottomBar(navHelper) },
        snackbarHost = {}
    ) {
        MainNavGraph(
            navHostController = navHelper.navHostController,
            listScreenContent = { ListScreen(viewModel, navHelper) },
            detailScreenContent = { DetailScreen(viewModel) },
            favoriteScreenContent = { FavoriteScreen(viewModel) }
        )
    }
}