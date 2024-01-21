package com.maxkor.interonnection.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.maxkor.interonnection.createLog

fun NavGraphBuilder.nestedNavGraph(
    listScreenContent: @Composable () -> Unit,
    detailScreenContent: @Composable (String) -> Unit,
) {
    navigation(
        startDestination = Screen.List.route,
        route = Screen.HomeGraph.route
    ) {
        composable(Screen.List.route) {
            listScreenContent()
        }
        composable(Screen.Detail.route) {
            val dataModelId = it.arguments?.getString(Screen.Detail.ARG_ID_KEY)
                ?: throw Exception(
                    "Navigation to detail screen failed dataModelId = null"
                )
            detailScreenContent(dataModelId)
        }
    }
}