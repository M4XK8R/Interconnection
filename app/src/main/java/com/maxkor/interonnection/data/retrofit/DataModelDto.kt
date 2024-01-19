package com.maxkor.interonnection.data.retrofit

import com.squareup.moshi.Json

data class DataModelDto(

    @Json(name = "id")
    val id: Int,

    @Json(name = "fullName")
    val fullName: String,

    @Json(name = "imageUrl")
    val imageUrl: String,

    var extraText: String = "",

    var isFavorite: Boolean = false
) {
    companion object {
        val initialData = DataModelDto(0, "", "")
    }
}
