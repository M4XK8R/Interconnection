package com.maxkor.interonnection.navigation

private const val ROUTE_HOME = "home"
private const val ROUTE_FAVORITE = "favorite"

sealed class Screen(val route: String) {

    data object Home : Screen(ROUTE_HOME)
    data object Favorite : Screen(ROUTE_FAVORITE)
}
