package com.maxkor.interonnection.ui.screens.base

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.interonnection.domain.usecases.GetErrorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getErrorsUseCase: GetErrorsUseCase
) : ViewModel() {

    val snackbarHostState = SnackbarHostState()

    val errorMessage: State<String> = getErrorsUseCase()

    init {
        viewModelScope.launch {
            delay(3000L)
            snackbarHostState.showSnackbar(errorMessage.value)
        }
    }
}
