package com.maxkor.interonnection.domain

interface MainRepository {

    suspend fun getData(hasInternetConnection: Boolean): List<DataModel>

    suspend fun saveData(dataList: List<DataModel>)
}