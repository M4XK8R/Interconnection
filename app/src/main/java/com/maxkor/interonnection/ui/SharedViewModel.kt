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

    private val _currentElement = mutableStateOf(DataModel.initialData)
    val currentElement: State<DataModel> = _currentElement

    init {
        createLog("viewmodel init")
        loadDataList()
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
        }
    }
}