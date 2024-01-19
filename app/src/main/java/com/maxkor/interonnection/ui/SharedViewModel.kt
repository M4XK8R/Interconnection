package com.maxkor.interonnection.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.interonnection.data.retrofit.ApiFactory
import com.maxkor.interonnection.data.retrofit.DataModelDto
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private val _dataLIst = mutableStateOf(emptyList<DataModelDto>())
    val dataLIst: State<List<DataModelDto>> = _dataLIst

    private val _stableList = mutableStateOf(emptyList<DataModelDto>())
    val stableList: State<List<DataModelDto>> = _stableList

    val searchedText = mutableStateOf("")

    private val _currentElement = mutableStateOf(DataModelDto.initialData)
    val currentElement: State<DataModelDto> = _currentElement

    init {
        loadDataList()
    }

    fun removeItem(item: DataModelDto) {
        val currentList = _dataLIst.value.toMutableList()
        currentList.remove(item)
        _dataLIst.value = currentList
    }

    fun updateData(newList: List<DataModelDto>) {
        _dataLIst.value = newList
    }

    fun passCurrentElement(dataModel: DataModelDto) {
        _currentElement.value = dataModel
    }

    private fun loadDataList() {
        viewModelScope.launch {
            _dataLIst.value = ApiFactory.apiService.getActorsList()
            _stableList.value = _dataLIst.value
        }
    }
}