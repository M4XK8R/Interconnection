package com.maxkor.interonnection.data.retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class DataModelDto(

    @Json(name = "rank")
    val id: Int,

    @Json(name = "symbol")
    val fullName: String,

    @Json(name = "price")
    val price: String,

    @Json(name = "iconUrl")
    val imageUrl: String,

    var extraText: String = "",

    var isFavorite: Boolean = false
)


