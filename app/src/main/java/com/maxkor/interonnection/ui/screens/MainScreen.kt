package com.maxkor.interonnection.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.maxkor.interonnection.navigation.MyNavGraph
import com.maxkor.interonnection.navigation.NavigationHelper
import com.maxkor.interonnection.ui.screens.bar.MyBottomBar
import com.maxkor.interonnection.ui.screens.favorite.FavoriteScreen
import com.maxkor.interonnection.ui.screens.list.ListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navHelper = NavigationHelper.rememberNavigationState()

    Scaffold(bottomBar = { MyBottomBar(navHelper) }) {
        MyNavGraph(
            navHostController = navHelper.navHostController,
            homeScreenContent = { ListScreen() },
            favoriteScreenContent = { FavoriteScreen() }
        )
    }
}