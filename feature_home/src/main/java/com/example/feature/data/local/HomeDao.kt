package com.example.feature.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature.data.local.entity.CacheDataSource
import com.example.feature.presentation.home.view_model.SortType
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {

    fun fetchCache(
        department: String,
        sortType: SortType,
        searchQuery: String
    ): Flow<List<CacheDataSource>> =
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
    fun getUsersSortedByName(department: String, searchQuery: String): Flow<List<CacheDataSource>>

    // TODO: replace :firstname by :date for SortType
    @Query("SELECT * FROM user_db WHERE department LIKE '%' || :department || '%' AND firstName lIKE '%' || :searchQuery || '%' ORDER BY timestamp ASC")
    fun getUsersSortedByDate(department: String, searchQuery: String): Flow<List<CacheDataSource>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(users: List<CacheDataSource>)

    @Query("DELETE FROM user_db")
    suspend fun clearCache()
}