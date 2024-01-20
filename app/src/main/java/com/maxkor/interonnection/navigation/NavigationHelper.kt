package com.maxkor.interonnection.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationHelper(
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToDetail(dataModelId: String) {
        navHostController.navigate(Screen.Detail.getRouteWithArgs(dataModelId)) {
//            popUpTo(navHostController.graph.findStartDestination().id) {
//                saveState = true
//            }
//            launchSingleTop = true
//            restoreState = true
        }
    }

    companion object {
        @Composable
        fun rememberNavigationState(
            navHostController: NavHostController = rememberNavController()
        ): NavigationHelper {
            return remember {
                NavigationHelper(navHostController)
            }
        }
    }
}










