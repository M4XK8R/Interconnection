package com.maxkor.interonnection.ui.screens.bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.maxkor.interonnection.R
import com.maxkor.interonnection.navigation.Screen

sealed class BottomNavigationItem(
    val titleResId: Int,
    val icon: ImageVector,
    val screen: Screen
) {

    data object Home : BottomNavigationItem(
        titleResId = R.string.home,
        icon = Icons.Filled.Home,
        screen = Screen.HomeGraph
    )

    data object Favorite : BottomNavigationItem(
        titleResId = R.string.favorite,
        icon = Icons.Filled.Favorite,
        screen = Screen.Favorite
    )

}





