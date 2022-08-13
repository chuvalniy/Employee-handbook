package com.example.feature.data.local.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature.data.local.cache.entity.CacheDataSource
import com.example.feature.presentation.home.model.SortType

@Dao
interface HomeDao {

    suspend fun fetchCache(
        department: String,
        sortType: SortType,
        searchQuery: String
    ): List<CacheDataSource> =
        when (sortType) {
            SortType.BY_NAME -> getUsersSortedByName(
                department = department,
                searchQuery = searchQuery
            )
            SortType.BY_DATE -> getUsersSortedByDate(
                department = department,
                searchQuery = searchQuery
            )
        }

    @Query("SELECT * FROM user_db WHERE department LIKE '%' || :department || '%' AND firstName lIKE '%' || :searchQuery || '%' ORDER BY firstName ASC")
    suspend fun getUsersSortedByName(department: String, searchQuery: String): List<CacheDataSource>

    // TODO: replace :firstname by :date for SortType
    @Query("SELECT * FROM user_db WHERE department LIKE '%' || :department || '%' AND firstName lIKE '%' || :searchQuery || '%' ORDER BY timestamp ASC")
    suspend fun getUsersSortedByDate(department: String, searchQuery: String): List<CacheDataSource>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(users: List<CacheDataSource>)

    @Query("DELETE FROM user_db")
    suspend fun clearCache()
}