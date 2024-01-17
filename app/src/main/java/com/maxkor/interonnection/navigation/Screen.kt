package com.maxkor.interonnection.navigation

private const val ROUTE_HOME = "home"
private const val ROUTE_LIST = "list"
private const val ROUTE_DETAIL = "detail"
private const val ROUTE_FAVORITE = "favorite"

sealed class Screen(val route: String) {

    data object HomeGraph : Screen(ROUTE_HOME)
    data object List : Screen(ROUTE_LIST)
    data object Detail : Screen(ROUTE_DETAIL)

    data object Favorite : Screen(ROUTE_FAVORITE)
}
