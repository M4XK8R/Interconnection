package com.maxkor.interonnection.ui.screens.detail

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.domain.DataModel
import com.maxkor.interonnection.domain.usecases.AddDescriptionUseCase
import com.maxkor.interonnection.domain.usecases.AddToFavoritesUseCase
import com.maxkor.interonnection.domain.usecases.GetDataReactiveUseCase
import com.maxkor.interonnection.domain.usecases.GetDataUseCase
import com.maxkor.interonnection.domain.usecases.GetElementUseCase
import com.maxkor.interonnection.domain.usecases.RemoveFromFavoritesUseCase
import com.maxkor.interonnection.domain.usecases.SaveDataUseCase
import com.maxkor.interonnection.helpers.InternetChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val addDescriptionUseCase: AddDescriptionUseCase,
    private val getElementUseCase: GetElementUseCase
) : ViewModel() {

    private val _currentElement = mutableStateOf(DataModel.initial)
    val currentElement: State<DataModel> = _currentElement

    override fun onCleared() {
        createLog("DetailViewModel onCleared")
        super.onCleared()
    }

    fun addToFavorites(dataModel: DataModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addToFavoritesUseCase(dataModel)
        }
    }

    fun removeFromFavorites(dataModel: DataModel) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFromFavoritesUseCase(dataModel)
        }
    }

    fun addDescription(dataModel: DataModel, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addDescriptionUseCase(dataModel, text)
        }
    }

    fun getElement(dataModelId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _currentElement.value = getElementUseCase(dataModelId)
        }
    }
}