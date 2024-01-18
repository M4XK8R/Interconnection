package com.maxkor.interonnection.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.data.retrofit.ApiFactory
import com.maxkor.interonnection.data.retrofit.DataModel
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private val _dataLIst = mutableStateOf(emptyList<DataModel>())
    val dataLIst: State<List<DataModel>> = _dataLIst

    private val _stableList = mutableStateOf(emptyList<DataModel>())
    val stableList: State<List<DataModel>> = _stableList

    val searchedText = mutableStateOf("")

    private val _currentElement = mutableStateOf(DataModel.initialData)
    val currentElement: State<DataModel> = _currentElement

    init {
        loadDataList()
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

    private fun loadDataList() {
        viewModelScope.launch {
            _dataLIst.value = ApiFactory.apiService.getActorsList()
            _stableList.value = _dataLIst.value
        }
    }
}