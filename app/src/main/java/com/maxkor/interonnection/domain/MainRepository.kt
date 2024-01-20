package com.maxkor.interonnection.domain

import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getDataReactive(): Flow<List<DataModel>>

    suspend fun insertToDb(dataModel: DataModel)

    suspend fun loadDataFromServerToDb(hasInternetConnection: Boolean)

    suspend fun getElement(modelId: Int): DataModel

    suspend fun getErrors()

}