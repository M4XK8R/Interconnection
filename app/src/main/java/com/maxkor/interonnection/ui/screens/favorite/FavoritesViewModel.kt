package com.maxkor.interonnection.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.domain.models.DataModel
import com.maxkor.interonnection.domain.usecases.AddDescriptionUseCase
import com.maxkor.interonnection.domain.usecases.AddToFavoritesUseCase
import com.maxkor.interonnection.domain.usecases.GetDataReactiveUseCase
import com.maxkor.interonnection.domain.usecases.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getDataReactiveUseCase: GetDataReactiveUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val addDescriptionUseCase: AddDescriptionUseCase,
) : ViewModel() {

    val dataListReactive = getDataReactiveUseCase()

    override fun onCleared() {
        createLog("FavoritesViewModel onCleared")
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
}