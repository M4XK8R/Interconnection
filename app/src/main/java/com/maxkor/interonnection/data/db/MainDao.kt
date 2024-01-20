package com.maxkor.interonnection.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maxkor.interonnection.domain.DataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

@Dao
interface MainDao {

    @Query("SELECT * FROM main_data_table")
    suspend fun getAll(): List<DataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllData(dataList: List<DataEntity>)

    @Query("SELECT * FROM main_data_table")
    fun getDataReactive(): Flow<List<DataEntity>>
}