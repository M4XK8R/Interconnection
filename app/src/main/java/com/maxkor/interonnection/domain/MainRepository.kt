package com.maxkor.interonnection.domain

interface MainRepository {

    suspend fun getData(offlineMode: Boolean): List<DataModel>

    suspend fun saveToInternalDb(dataList: List<DataModel>)
}