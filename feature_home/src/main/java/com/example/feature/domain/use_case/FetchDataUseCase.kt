package com.example.feature.domain.use_case

import com.example.feature.domain.repository.HomeRepository
import com.example.feature.presentation.home.view_model.SortType

class FetchDataUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(department: String,
                        sortType: SortType,
                        searchQuery: String,
                        refreshData: Boolean
    ) = repository.fetchUsers(department, sortType, searchQuery, refreshData)
}