package com.maxkor.interonnection.ui.screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maxkor.interonnection.navigation.MainNavGraph
import com.maxkor.interonnection.navigation.NavigationHelper
import com.maxkor.interonnection.ui.SharedViewModel
import com.maxkor.interonnection.ui.screens.bar.MyBottomBar
import com.maxkor.interonnection.ui.screens.detail.DetailScreen
import com.maxkor.interonnection.ui.screens.favorite.FavoriteScreen
import com.maxkor.interonnection.ui.screens.list.ListScreen
import javax.inject.Inject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

//    val viewModel: SharedViewModel = viewModel(LocalContext.current as ComponentActivity)
    val viewModel: SharedViewModel = hiltViewModel()

    val navHelper = NavigationHelper.rememberNavigationState()

    Scaffold(bottomBar = { MyBottomBar(navHelper) }) {

        MainNavGraph(
            navHostController = navHelper.navHostController,
            listScreenContent = { ListScreen(viewModel, navHelper) },
            detailScreenContent = { DetailScreen(viewModel) },
            favoriteScreenContent = { FavoriteScreen(viewModel) }
        )
    }
}