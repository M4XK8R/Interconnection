package com.maxkor.interonnection.ui.screens.composables.card

sealed class DataCardState {
    data object Favorites: DataCardState()
    data object List: DataCardState()
}