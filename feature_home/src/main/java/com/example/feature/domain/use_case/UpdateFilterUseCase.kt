package com.example.feature.domain.use_case

import com.example.feature.domain.repository.HomeRepository

class UpdateFilterUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(departmentFilter: String) = repository.updateFilter(departmentFilter)
}