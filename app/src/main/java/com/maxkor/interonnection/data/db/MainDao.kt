package com.maxkor.interonnection.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MainDao {

    @Query("SELECT * FROM main_data_table")
    suspend fun getAll(): List<DataEntity>

    @Delete
    suspend fun deleteElement(dataEntity: DataEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllData(dataList: List<DataEntity>)

}