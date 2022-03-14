package com.example.kode_test_app.domain.repository

import com.example.kode_test_app.domain.model.User
import com.example.kode_test_app.core.utils.SortType
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(department: String, sortType: SortType, searchQuery: String): Flow<List<User>>

    suspend fun refreshData()

    fun getUserById(id: String): Flow<User>
}