package com.maxkor.interonnection.domain

data class DataModel(
    val id: Int,
    val fullName: String,
    val imageUrl: String,
    var extraText: String,
    var isFavorite: Boolean
)
