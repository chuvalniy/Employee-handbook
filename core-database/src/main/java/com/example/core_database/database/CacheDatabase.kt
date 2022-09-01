package com.example.core_database.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [com.example.core_database.model.CacheUser::class],
    version = 1
)
abstract class CacheDatabase : RoomDatabase() {

    abstract val dao: CacheDao

    companion object {
        const val DATABASE_NAME = "user_db"
    }
}