package com.example.feature.domain.repository

import com.example.core.utils.Resource
import com.example.feature.domain.model.User
import com.example.feature.presentation.utils.SortType
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(department: String, sortType: SortType, searchQuery: String): Flow<Resource<List<User>>>

    fun getUserById(id: String): Flow<User>
}