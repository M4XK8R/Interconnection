package com.maxkor.interonnection.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.domain.DataModel
import com.maxkor.interonnection.domain.MainRepository
import com.maxkor.interonnection.helpers.InternetChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: MainRepository,
    internetChecker: InternetChecker
) : ViewModel() {

    val snackbarHostState = SnackbarHostState()

    val dataListReactive = repository.getDataReactive()

//    private val _dataLIst = mutableStateOf(emptyList<DataModel>())
//    val dataLIst: State<List<DataModel>> = _dataLIst

    private val _stableList = mutableStateOf(emptyList<DataModel>())
    val stableList: State<List<DataModel>> = _stableList

    val searchedText = mutableStateOf("")

    private val _currentElement = mutableStateOf(DataModel.initial)
    val currentElement: State<DataModel> = _currentElement

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
        createLog("hasInternetConnection = $hasInternetConnection")
        loadAndSetUpData(hasInternetConnection)
    }

    fun getElement(modelId: String) {
        viewModelScope.launch {
            _currentElement.value = repository.getElement(modelId.toInt())
        }
    }

    fun addToFavorites(dataModel: DataModel) {
        val newDataModel = dataModel.copy(
            isFavorite = !dataModel.isFavorite
        )
        insertToDb(newDataModel)
    }

    fun removeFromFavorites(dataModel: DataModel) {
        val newDataModel = dataModel.copy(
            extraText = DataModel.DEFAULT_EXTRA_TEXT,
            isFavorite = !dataModel.isFavorite
        )
        insertToDb(newDataModel)
    }

    fun addDescription(dataModel: DataModel, text: String) {
        val newDataModel = dataModel.copy(
            extraText = text
        )
        insertToDb(newDataModel)
    }

//    fun passCurrentElement(dataModel: DataModel) {
//        _currentElement.value = dataModel
//    }

    private fun insertToDb(dataModel: DataModel) {
        viewModelScope.launch {
            repository.insertToDb(dataModel)
        }
    }

    private fun loadAndSetUpData(hasInternetConnection: Boolean) {
        viewModelScope.launch {
            val currentDataFromInternet = repository.getData(hasInternetConnection)
            _stableList.value = currentDataFromInternet
            repository.saveData(currentDataFromInternet)
        }
    }
}