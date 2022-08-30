package com.example.core_database.database

import androidx.room.withTransaction
import com.example.core.utils.SortType
import com.example.core_database.model.CacheUser

interface CacheDataSource {

    suspend fun fetchCache(
        department: String,
        sortType: SortType,
        searchQuery: String
    ): List<CacheUser>

    suspend fun updateData(cache: List<CacheUser>)

    class RoomCacheDataSource(
        private val database: CacheDatabase,
        private val dao: CacheDao,
    ) : CacheDataSource {

        override suspend fun fetchCache(
            department: String,
            sortType: SortType,
            searchQuery: String
        ) = dao.fetchCache(department, sortType, searchQuery)

        override suspend fun updateData(cache: List<CacheUser>) {
            database.withTransaction {
                dao.insertCache(cache)
                dao.clearCache()
            }
        }
    }
}