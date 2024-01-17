package com.maxkor.interonnection.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.nestedNavGraph(
    listScreenContent: @Composable () -> Unit,
    detailScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.List.route,
        route = Screen.HomeGraph.route
    ) {
        composable(Screen.List.route) {
            listScreenContent()
        }
        composable(Screen.Detail.route) {
            detailScreenContent()
        }
    }
}