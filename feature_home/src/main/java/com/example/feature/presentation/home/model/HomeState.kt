package com.example.feature.presentation.home.model

import com.example.core.ui.UiState
import com.example.feature.domain.model.DepartmentList
import com.example.feature.domain.model.DomainDataSource

data class HomeState(
    val data: List<DomainDataSource> = emptyList(),
    val loadingState: LoadingState = LoadingState.NONE,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val sortType: SortType = SortType.BY_NAME,
    val departmentFilter: String = DepartmentList.departmentListDatabase[0],
) : UiState

enum class LoadingState {
    SHIMMER, SNACKBAR, NONE
}

enum class SortType {
    BY_NAME,
    BY_DATE
}