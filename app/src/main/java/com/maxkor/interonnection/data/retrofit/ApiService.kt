package com.maxkor.interonnection.data.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

//private const val END_POINT_URL = "/api/v2/Characters"
private const val END_POINT_URL = "v2/coins"
private const val API_KEY = "coinranking9b6a731a17c7fcfa9ff07829122ba58eed22a0d0806113ee"

interface ApiService {

    @Headers("x-access-token: $API_KEY")
    @GET(END_POINT_URL)
//    suspend fun getResponse(): Response<List<DataModelDto>>
    suspend fun getResponse(): Response<CoinResponse>
}