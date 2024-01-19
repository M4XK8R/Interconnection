package com.maxkor.interonnection.data

import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.data.db.InternalDataBase
import com.maxkor.interonnection.data.retrofit.ApiService
import com.maxkor.interonnection.domain.DataModel
import com.maxkor.interonnection.domain.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val db: InternalDataBase,
    private val api: ApiService
) : MainRepository {

    override suspend fun getData(hasInternetConnection: Boolean): List<DataModel> {
        createLog("getData")
        val dataList = mutableListOf<DataModel>()
        if (hasInternetConnection) {
            createLog("onlineMode")
            api.getDataList().forEach { dto ->
                dataList.add(PojoMapper.dtoToModel(dto))
            }
        } else {
            createLog("offlineMode")
            db.getMainDao().getAll().forEach { entity ->
                dataList.add(PojoMapper.entityToModel(entity))
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