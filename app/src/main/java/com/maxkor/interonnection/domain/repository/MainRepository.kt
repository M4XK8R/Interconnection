package com.maxkor.interonnection.domain.repository

import com.maxkor.interonnection.domain.models.DataModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getDataReactive(): Flow<List<DataModel>>

    suspend fun insertToDb(dataModel: DataModel)

    suspend fun loadDataFromServerToDb(hasInternetConnection: Boolean)

    suspend fun getElement(modelId: Int): DataModel

    suspend fun getErrors()

}