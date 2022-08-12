package com.example.feature.domain.use_case

import com.example.feature.domain.repository.HomeRepository

class FetchFilterUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke() = repository.fetchFilter()
}