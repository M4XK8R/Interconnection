package com.maxkor.interonnection.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface MainRepository {

    suspend fun getData(hasInternetConnection: Boolean): List<DataModel>

    suspend fun saveData(dataList: List<DataModel>)

    suspend fun getErrors()

    fun getDataReactive(): Flow<List<DataModel>>
}