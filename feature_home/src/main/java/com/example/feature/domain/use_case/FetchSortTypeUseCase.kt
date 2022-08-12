package com.example.feature.domain.use_case

import com.example.feature.domain.repository.HomeRepository

class FetchSortTypeUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke() = repository.fetchSortType()
}