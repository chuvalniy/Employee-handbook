package com.example.kode_test_app.domain.repository

import com.example.kode_test_app.domain.model.User
import com.example.kode_test_app.utils.Resource
import com.example.kode_test_app.utils.SortType
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(department: String, sortType: SortType, searchQuery: String): Flow<List<User>>

    suspend fun refreshData()
}