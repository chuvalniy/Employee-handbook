package com.example.feature.domain.use_case

import com.example.feature.domain.repository.UserRepository
import com.example.feature.presentation.utils.SortType

class GetUserByIdUseCase(
    private val repository: UserRepository
) {

    operator fun invoke(id: String) = repository.getUserById(id)
}