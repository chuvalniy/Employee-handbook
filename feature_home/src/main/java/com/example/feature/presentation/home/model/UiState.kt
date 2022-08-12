package com.example.feature.presentation.home.model

import com.example.feature.domain.model.DepartmentList
import com.example.feature.domain.model.DomainDataSource
import com.example.feature.presentation.home.view_model.SortType

data class UiState(
    val data: List<DomainDataSource> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val sortType: SortType = SortType.BY_NAME,
    val departmentFilter: String = DepartmentList.departmentListDatabase[0],
)