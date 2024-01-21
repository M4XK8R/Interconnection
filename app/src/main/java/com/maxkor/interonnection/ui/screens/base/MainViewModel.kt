package com.maxkor.interonnection.ui.screens.base

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.interonnection.domain.usecases.CheckInternetUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    checkInternetUseCase: CheckInternetUseCase
) : ViewModel() {

    val snackbarHostState = SnackbarHostState()

    init {
        val hasInternetConnection = checkInternetUseCase()
        if (!hasInternetConnection) {
            viewModelScope.launch {
                snackbarHostState.showSnackbar("NO INTERNET CONNECTION")
            }
        } else {
            viewModelScope.launch {
                snackbarHostState.showSnackbar("INTERNET CONNECTION")
            }
        }
    }
}