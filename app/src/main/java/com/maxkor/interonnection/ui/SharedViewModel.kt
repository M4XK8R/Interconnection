package com.maxkor.interonnection.ui

import android.app.Application
import android.content.Context
import android.content.ServiceConnection
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.interonnection.App
import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.domain.DataModel
import com.maxkor.interonnection.domain.MainRepository
import com.maxkor.interonnection.ui.screens.InternetChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    application: Application,
    private val repository: MainRepository
) : AndroidViewModel(application) {

    private val _dataLIst = mutableStateOf(emptyList<DataModel>())
    val dataLIst: State<List<DataModel>> = _dataLIst

    private val _stableList = mutableStateOf(emptyList<DataModel>())
    val stableList: State<List<DataModel>> = _stableList

    val searchedText = mutableStateOf("")

    private val _currentElement = mutableStateOf(DataModel.initial)
    val currentElement: State<DataModel> = _currentElement

    init {
        val hasInternetConnection = InternetChecker.isNetworkAvailable(application)
        createLog("hasInternetConnection = $hasInternetConnection")
        loadDataList(hasInternetConnection)
    }

    override fun onCleared() {
        // start service? TODO()
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveToInternalDb(_dataLIst.value)
        }
        super.onCleared()
    }

    fun removeItem(item: DataModel) {
        val currentList = _dataLIst.value.toMutableList()
        currentList.remove(item)
        _dataLIst.value = currentList
    }

    fun updateData(newList: List<DataModel>) {
        _dataLIst.value = newList
    }

    fun passCurrentElement(dataModel: DataModel) {
        _currentElement.value = dataModel
    }

    private fun loadDataList(hasInternetConnection: Boolean) {
        viewModelScope.launch {
            _dataLIst.value = repository.getData(hasInternetConnection)
            _stableList.value = _dataLIst.value
        }
    }
}