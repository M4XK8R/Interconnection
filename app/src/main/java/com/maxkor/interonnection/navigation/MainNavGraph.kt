package com.maxkor.interonnection.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    listScreenContent: @Composable () -> Unit,
    detailScreenContent: @Composable (String) -> Unit,
    favoriteScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeGraph.route
    ) {
        nestedNavGraph(
            listScreenContent =  listScreenContent,
            detailScreenContent = detailScreenContent
        )
        composable(Screen.Favorite.route) {
            favoriteScreenContent()
        }
    }
}