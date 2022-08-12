package com.example.feature.domain.use_case

import com.example.feature.domain.repository.HomeRepository
import com.example.feature.presentation.home.view_model.SortType

class UpdateSortTypeUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(sortType: SortType) = repository.updateSortType(sortType)
}