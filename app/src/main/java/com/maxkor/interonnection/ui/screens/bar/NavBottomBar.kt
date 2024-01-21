package com.maxkor.interonnection.ui.screens.bar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.maxkor.interonnection.navigation.NavigationHelper

@Composable
fun MyBottomBar(navHelper: NavigationHelper) {

    val icons = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Favorite
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        val navBackStackEntry by navHelper.navHostController
            .currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route

        icons.forEach() { item ->
            val currentRoute = item.screen.route
            val isSelected = navBackStackEntry?.destination?.hierarchy?.any {
                it.route == currentRoute
            } ?: false
            NavigationBarItem(
//                selected = currentRoute == route,
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navHelper.navigateTo(currentRoute)
                    }
                },

                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.icon.name,
                        modifier = Modifier.size(24.dp)
                    )
                },

                label = {
                    Text(
                        text = stringResource(id = item.titleResId),
                        fontSize = TextUnit(15f, TextUnitType.Sp),
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onBackground,
                    unselectedIconColor = MaterialTheme.colorScheme.background,
                )
            )
        }
    }
}









