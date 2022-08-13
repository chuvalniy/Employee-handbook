package com.example.feature.presentation.home.model

import com.example.feature.domain.model.DepartmentList
import com.example.feature.domain.model.DomainDataSource

data class UiState(
    val data: List<DomainDataSource> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isInit: Boolean = false,
    val fetchFromRemote: Boolean = false,
    val searchQuery: String = "",
    val sortType: SortType = SortType.BY_NAME,
    val departmentFilter: String = DepartmentList.departmentListDatabase[0],
)