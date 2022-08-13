package com.example.feature.domain.repository

import com.example.core.utils.Resource
import com.example.feature.domain.model.DomainDataSource
import com.example.feature.presentation.home.model.SortType
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun fetchData(
        department: String,
        sortType: SortType,
        searchQuery: String,
        fetchFromRemote: Boolean,
    ): Flow<Resource<List<DomainDataSource>>>

    fun fetchSortType(): SortType

    fun updateSortType(sortType: SortType)
}