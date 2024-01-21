package com.maxkor.interonnection.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "main_data_table")
data class DataEntity(
    @PrimaryKey
    val id: Int,
    val fullName: String,
    val price: String,
    val imageUrl: String,
    var extraText: String,
    var isFavorite: Boolean
)
