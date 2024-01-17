package com.maxkor.interonnection.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.maxkor.interonnection.navigation.MainNavGraph
import com.maxkor.interonnection.navigation.NavigationHelper
import com.maxkor.interonnection.ui.screens.bar.MyBottomBar
import com.maxkor.interonnection.ui.screens.detail.DetailScreen
import com.maxkor.interonnection.ui.screens.favorite.FavoriteScreen
import com.maxkor.interonnection.ui.screens.list.ListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navHelper = NavigationHelper.rememberNavigationState()

    Scaffold(bottomBar = { MyBottomBar(navHelper) }) {

        MainNavGraph(
            navHostController = navHelper.navHostController,
            listScreenContent = { ListScreen(navHelper) },
            detailScreenContent = { DetailScreen() },
            favoriteScreenContent = { FavoriteScreen() }
        )
    }
}