package com.example.kode_test_app.domain.repository

import com.example.kode_test_app.domain.model.User
import com.example.kode_test_app.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<Resource<List<User>>>
}