package com.maxkor.interonnection.data.retrofit

import com.squareup.moshi.Json

data class DataModel(

    @Json(name = "id")
    val id: Int,

//    @Json(name = "firstName")
//    val firstName: String?,
//
//    @Json(name = "lastName")
//    val lastName: String?,

    @Json(name = "fullName")
    val fullName: String,

    @Json(name = "imageUrl")
    val imageUrl: String,

    var extraText: String = "",

    var isFavorite: Boolean = false
) {
    companion object {
        val initialData = DataModel(0, "", "")
    }
}
