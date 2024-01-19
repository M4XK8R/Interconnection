package com.maxkor.interonnection.data.db

import com.maxkor.interonnection.data.PojoMapper
import com.maxkor.interonnection.data.retrofit.ApiService
import com.maxkor.interonnection.domain.DataModel
import com.maxkor.interonnection.domain.MainRepository

class MainRepositoryImpl(
    private val db: InternalDataBase,
    private val api: ApiService
) : MainRepository {

    override suspend fun getData(offlineMode: Boolean): List<DataModel> {
        val dataList = mutableListOf<DataModel>()
        if (offlineMode) {
            db.getMainDao().getAll().forEach { entity ->
                dataList.add(PojoMapper.entityToModel(entity))
            }
        } else {
            api.getDataList().forEach { dto ->
                dataList.add(PojoMapper.dtoToModel(dto))
            }
        }
        return dataList.toList()
    }

    override suspend fun saveToInternalDb(dataList: List<DataModel>) {
        val currentList = getData(false)
        val convertedList = currentList.map { model ->
            PojoMapper.modelToEntity(model)
        }
        db.getMainDao().insertAllData(convertedList)
    }


}