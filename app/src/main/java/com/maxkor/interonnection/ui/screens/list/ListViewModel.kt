package com.maxkor.interonnection.ui.screens.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.domain.DataModel
import com.maxkor.interonnection.domain.usecases.AddDescriptionUseCase
import com.maxkor.interonnection.domain.usecases.AddToFavoritesUseCase
import com.maxkor.interonnection.domain.usecases.GetDataReactiveUseCase
import com.maxkor.interonnection.domain.usecases.GetDataUseCase
import com.maxkor.interonnection.domain.usecases.RemoveFromFavoritesUseCase
import com.maxkor.interonnection.domain.usecases.SaveDataUseCase
import com.maxkor.interonnection.helpers.InternetChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getDataReactiveUseCase: GetDataReactiveUseCase,
    private val getDataUseCase: GetDataUseCase,
    private val saveDataUseCase: SaveDataUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val addDescriptionUseCase: AddDescriptionUseCase,
    internetChecker: InternetChecker
) : ViewModel() {

    val dataListReactive = getDataReactiveUseCase()

    val searchedText = mutableStateOf("")

    init {
        val hasInternetConnection = internetChecker.isNetworkAvailable()
        loadAndSetUpData(hasInternetConnection)
    }

    override fun onCleared() {
        createLog("ListViewModel onCleared")
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

    private fun loadAndSetUpData(hasInternetConnection: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val actualData = getDataUseCase(hasInternetConnection)
            saveDataUseCase(actualData)
        }
    }
}
