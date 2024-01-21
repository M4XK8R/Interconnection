package com.maxkor.interonnection.data.repository

import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.data.db.DataEntity
import com.maxkor.interonnection.data.db.InternalDataBase
import com.maxkor.interonnection.data.mappers.PojoMapper
import com.maxkor.interonnection.data.retrofit.ApiService
import com.maxkor.interonnection.domain.DataModel
import com.maxkor.interonnection.domain.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val db: InternalDataBase,
    private val api: ApiService,
    private val mapper: PojoMapper
) : MainRepository {

    override fun getDataReactive(): Flow<List<DataModel>> {
        return db.getMainDao().getDataReactive().map { entityList ->
            entityList.map { entityModel ->
                mapper.entityToModel(entityModel)
            }
        }
    }

    override suspend fun insertToDb(dataModel: DataModel) {
        db.getMainDao().insert(mapper.modelToEntity(dataModel))
    }

    override suspend fun getElement(modelId: Int): DataModel {
        return mapper.entityToModel(db.getMainDao().getElement(modelId))
    }

    override suspend fun loadDataFromServerToDb(hasInternetConnection: Boolean) {
        createLog("loadDataFromServerToDb")
        if (hasInternetConnection) {
            try {
                val newData = mutableListOf<DataEntity>()
                val response = api.getResponse()
                createLog("response = ${response.body()}")
                if (response.isSuccessful) {
                    response.body()?.data?.coins?.forEach { dto ->
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
                e.localizedMessage?.let {
                    createLog("loadDataFromServerToDb exception = $it")
                }
            }
        }
    }

    override suspend fun getErrors() {
        //TODO
    }
}