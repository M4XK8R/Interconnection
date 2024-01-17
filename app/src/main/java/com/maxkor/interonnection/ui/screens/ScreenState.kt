package com.maxkor.interonnection.ui.screens

sealed class ScreenState {
    data object ListState : ScreenState()
    data object DetailState : ScreenState()
}