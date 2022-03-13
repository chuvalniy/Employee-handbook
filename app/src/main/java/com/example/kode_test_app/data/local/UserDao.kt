package com.example.kode_test_app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kode_test_app.domain.model.User
import com.example.kode_test_app.utils.SortType
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    fun getUsers(department: String, sortType: SortType, searchQuery: String): Flow<List<User>> =
        when (sortType) {
            SortType.BY_NAME -> getUsersSortedByName(department = department, searchQuery = searchQuery)
            SortType.BY_DATE -> getUsersSortedByDate(department = department, searchQuery = searchQuery)
        }

    @Query("SELECT * FROM user_db WHERE department LIKE '%' || :department || '%' AND firstName lIKE '%' || :searchQuery || '%' ORDER BY firstName ASC")
    fun getUsersSortedByName(department: String, searchQuery: String): Flow<List<User>>

    @Query("SELECT * FROM user_db WHERE department LIKE '%' || :department || '%' AND firstName lIKE '%' || :searchQuery || '%' ORDER BY firstName DESC")
    fun getUsersSortedByDate(department: String, searchQuery: String): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Query("DELETE FROM user_db")
    suspend fun deleteUsers()
}