package com.maxkor.interonnection.ui.screens

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.interonnection.helpers.InternetChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    internetChecker: InternetChecker
) : ViewModel() {

    val snackbarHostState = SnackbarHostState()

    init {
        val hasInternetConnection = internetChecker.isNetworkAvailable()
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