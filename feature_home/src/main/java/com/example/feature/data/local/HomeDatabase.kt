package com.example.feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature.data.local.entity.CacheDataSource

@Database(
    entities = [CacheDataSource::class],
    version = 1
)
abstract class HomeDatabase : RoomDatabase() {

    abstract val dao: HomeDao
}