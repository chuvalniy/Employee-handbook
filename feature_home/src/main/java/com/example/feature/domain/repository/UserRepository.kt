package com.example.feature.domain.repository

import com.example.core.utils.Resource
import com.example.feature.domain.model.DomainDataSource
import com.example.feature.presentation.home.view_model.SortType
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(department: String, sortType: SortType, searchQuery: String): Flow<Resource<List<DomainDataSource>>>

}