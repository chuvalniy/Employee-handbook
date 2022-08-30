package com.example.core_database.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.utils.SortType
import com.example.core_database.model.CacheUser

@Dao
interface CacheDao {

    suspend fun fetchCache(
        department: String,
        sortType: SortType,
        searchQuery: String
    ): List<CacheUser> =
        when (sortType) {
            SortType.BY_NAME -> getUsersSortedByName(
                department = department,
                searchQuery = searchQuery
            )
            SortType.BY_DATE -> getUsersSortedByBirthday(
                department = department,
                searchQuery = searchQuery
            )
        }

    @Query("SELECT * FROM ${CacheDatabase.DATABASE_NAME}" +
            " WHERE department LIKE '%' || :department || '%'" +
            " AND firstName LIKE '%' || :searchQuery || '%'" +
            " ORDER BY firstName ASC")
    suspend fun getUsersSortedByName(department: String, searchQuery: String): List<CacheUser>

    @Query("SELECT * FROM ${CacheDatabase.DATABASE_NAME}" +
            " WHERE department LIKE '%' || :department || '%'" +
            " AND firstName LIKE '%' || :searchQuery || '%'" +
            " ORDER BY SUBSTR(DATE('NOW'), 6) > SUBSTR(birthday, 6), SUBSTR(birthday, 6)")
    suspend fun getUsersSortedByBirthday(department: String, searchQuery: String): List<CacheUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(cache: List<CacheUser>)

    @Query("DELETE FROM ${CacheDatabase.DATABASE_NAME}")
    suspend fun clearCache()
}