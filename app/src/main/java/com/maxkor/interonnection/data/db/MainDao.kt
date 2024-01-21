package com.maxkor.interonnection.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Query("SELECT * FROM main_data_table")
    suspend fun getAll(): List<DataEntity>

    @Query("SELECT * FROM main_data_table where id= :modelId")
    suspend fun getElement(modelId: Int) : DataEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllData(dataList: List<DataEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dataList: DataEntity)

    @Query("SELECT * FROM main_data_table")
    fun getDataReactive(): Flow<List<DataEntity>>
}