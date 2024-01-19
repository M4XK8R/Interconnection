package com.maxkor.interonnection.data.retrofit

import retrofit2.http.GET

private const val END_POINT_URL = "/api/v2/Characters"

interface ApiService {

    @GET(END_POINT_URL)
    suspend fun getDataList(): List<DataModelDto>

}