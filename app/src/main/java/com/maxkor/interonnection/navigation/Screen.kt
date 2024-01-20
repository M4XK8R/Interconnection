package com.maxkor.interonnection.navigation

private const val ROUTE_HOME = "home"
private const val ROUTE_LIST = "list"
private const val ROUTE_DETAIL = "detail/{data_model_id}"
private const val ROUTE_FAVORITE = "favorite"

sealed class Screen(val route: String) {

    data object HomeGraph : Screen(ROUTE_HOME)

    data object List : Screen(ROUTE_LIST)

    data object Detail : Screen(ROUTE_DETAIL) {
        const val ARG_ID_KEY = "data_model_id"

        private const val ROUTE_FOR_ARGS = "detail"

        fun getRouteWithArgs(dataModelId: String): String {
            return "$ROUTE_FOR_ARGS/$dataModelId"
        }
    }

    data object Favorite : Screen(ROUTE_FAVORITE)
}
