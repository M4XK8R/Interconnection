package com.maxkor.interonnection.data.repository

import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.data.mappers.PojoMapper
import com.maxkor.interonnection.data.db.DataEntity
import com.maxkor.interonnection.data.db.InternalDataBase
import com.maxkor.interonnection.data.retrofit.ApiService
import com.maxkor.interonnection.domain.DataModel
import com.maxkor.interonnection.domain.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val db: InternalDataBase,
    private val api: ApiService,
    private val mapper: PojoMapper
) : MainRepository {

    override suspend fun getData(hasInternetConnection: Boolean): List<DataModel> {
        if (hasInternetConnection) {
            loadDataFromServerToDb()
        }
        val dataList = mutableListOf<DataModel>()
        db.getMainDao().getAll().forEach { entity ->
            dataList.add(mapper.entityToModel(entity))
        }
        return dataList.toList()
    }

    private suspend fun loadDataFromServerToDb() {
        try {
            val newData = mutableListOf<DataEntity>()
            val response = api.getResponse()
            if (response.isSuccessful) {
                response.body()?.forEach { dto ->
                    newData.add(mapper.dtoToEntity(dto))
                }
                val oldData = db.getMainDao().getAll()
                // TODO get first 50
                if (oldData.isNotEmpty()) {
                    if (newData.size != oldData.size) throw Exception(
                        "MainRepositoryImpl: the size of lists is different. Something went wrong"
                    )
                    for (index in oldData.indices) {
                        if (oldData[index].isFavorite) {
                            newData[index] = oldData[index]
                        }
                    }
                }
                db.getMainDao().insertAllData(newData)
            } else {
                //TODO
                response.errorBody()?.let { createLog(it.string()) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            e.localizedMessage?.let { createLog(it) }
        }
    }

    override suspend fun saveData(dataList: List<DataModel>) {
        val convertedList = dataList.map { model ->
            mapper.modelToEntity(model)
        }
        db.getMainDao().insertAllData(convertedList)
    }

    override suspend fun getErrors() {
//TODO
    }

    override fun getDataReactive(): Flow<List<DataModel>> {
        createLog("MainRepositoryImpl getDataReactive")
        return db.getMainDao().getDataReactive().map { entityList ->
            entityList.map { entityModel ->
                mapper.entityToModel(entityModel)
            }
        }
    }

}