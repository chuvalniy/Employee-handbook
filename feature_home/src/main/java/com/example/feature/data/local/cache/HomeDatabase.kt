package com.example.feature.data.local.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature.data.local.cache.entity.CacheDataSource

@Database(
    entities = [CacheDataSource::class],
    version = 1
)
abstract class HomeDatabase : RoomDatabase() {

    abstract val dao: HomeDao

    companion object {
        const val DATABASE_NAME = "user_db"
    }
}