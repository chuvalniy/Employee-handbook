package com.example.feature.model

import com.example.core.presentation.UiState
import com.example.core.utils.SortType
import com.example.core_model.model.DomainUser

data class HomeState(
    val data: List<DomainUser> = emptyList(),
    val loadingState: LoadingState = LoadingState.NONE,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val sortType: SortType = SortType.BY_NAME,
    val departmentFilter: String = ""
) : UiState

enum class LoadingState {
    SHIMMER, SNACKBAR, NONE
}