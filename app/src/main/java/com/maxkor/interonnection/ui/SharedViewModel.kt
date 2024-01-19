package com.maxkor.interonnection.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.interonnection.domain.DataModel
import com.maxkor.interonnection.domain.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _dataLIst = mutableStateOf(emptyList<DataModel>())
    val dataLIst: State<List<DataModel>> = _dataLIst

    private val _stableList = mutableStateOf(emptyList<DataModel>())
    val stableList: State<List<DataModel>> = _stableList

    val searchedText = mutableStateOf("")

    private val _currentElement = mutableStateOf(DataModel.initial)
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
            _dataLIst.value = repository.getData(false)
            _stableList.value = _dataLIst.value
        }
    }
}