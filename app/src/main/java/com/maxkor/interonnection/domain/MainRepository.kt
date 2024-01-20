package com.maxkor.interonnection.domain

import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun insertToDb(dataModel: DataModel)

    suspend fun getData(hasInternetConnection: Boolean): List<DataModel>

    suspend fun getElement(modelId: Int) : DataModel

    suspend fun saveData(dataList: List<DataModel>)

    suspend fun getErrors()

    fun getDataReactive(): Flow<List<DataModel>>
}