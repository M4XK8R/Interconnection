package com.maxkor.interonnection.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataEntity::class], version = 1)
abstract class InternalDataBase : RoomDatabase(){
    abstract fun getMainDao(): MainDao
}