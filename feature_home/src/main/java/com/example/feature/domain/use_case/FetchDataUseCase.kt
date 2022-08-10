package com.example.feature.domain.use_case

import com.example.feature.domain.repository.UserRepository
import com.example.feature.presentation.home.view_model.SortType

class FetchDataUseCase(
    private val repository: UserRepository
) {

    operator fun invoke(department: String,
                        sortType: SortType,
                        searchQuery: String
    ) = repository.getUsers(department, sortType, searchQuery)
}