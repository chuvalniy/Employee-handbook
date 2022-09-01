package com.example.core_data.repository

import com.example.core.core.Resource
import com.example.core.utils.SortType
import com.example.core_model.DomainUser
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun fetchData(
        department: String,
        sortType: SortType,
        searchQuery: String,
    ): Flow<Resource<List<DomainUser>>>

}