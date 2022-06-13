package com.example.feature.domain.use_case

import com.example.feature.domain.repository.UserRepository
import com.example.feature.presentation.utils.SortType

class GetUsersUseCase(
    private val repository: UserRepository
) {

    operator fun invoke(department: String,
                        sortType: SortType,
                        searchQuery: String
    ) = repository.getUsers(department, sortType, searchQuery)
}