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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: MainRepository,
    internetChecker: InternetChecker
) : ViewModel() {

    val snackbarHostState = SnackbarHostState()

     val dataListReactive = repository.getDataReactive()

    private val _dataLIst = mutableStateOf(emptyList<DataModel>())
    val dataLIst: State<List<DataModel>> = _dataLIst

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
        loadDataList(hasInternetConnection)
    }

    fun saveData(dataList: List<DataModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveData(dataList)
        }
        updateData(dataList)
    }

    fun removeItem(item: DataModel) {
        val currentList = _dataLIst.value.toMutableList()
        currentList.remove(item)
        saveData(currentList)
        updateData(currentList)
    }

    fun passCurrentElement(dataModel: DataModel) {
        _currentElement.value = dataModel
    }

    private fun updateData(newList: List<DataModel>) {
        _dataLIst.value = newList
    }

    private fun loadDataList(hasInternetConnection: Boolean) {
        viewModelScope.launch {
            _dataLIst.value = repository.getData(hasInternetConnection)
            repository.saveData(_dataLIst.value)
            _stableList.value = _dataLIst.value
        }
    }
}