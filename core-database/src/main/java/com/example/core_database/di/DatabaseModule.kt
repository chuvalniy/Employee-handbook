package com.example.core_database.di

import android.content.Context
import androidx.room.Room
import com.example.core_database.database.CacheDatabase
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @Provides
    fun provideCacheDatabase(context: Context): CacheDatabase {
        return Room.databaseBuilder(
            context,
            CacheDatabase::class.java,
            CacheDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideCacheDao(database: CacheDatabase) = database.dao
}