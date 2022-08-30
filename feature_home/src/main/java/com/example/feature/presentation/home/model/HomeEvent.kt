package com.example.feature.presentation.home.model

import com.example.core.ui.UiEvent
import com.example.feature.domain.model.DomainDataSource

sealed class HomeEvent : UiEvent {
    data class SortTypeSelected(val sortType: SortType): HomeEvent()
    data class SearchQueryChanged(val query: String): HomeEvent()
    data class DepartmentSelected(val department: String): HomeEvent()
    data class UserItemClicked(val user: DomainDataSource): HomeEvent()
    object FilterButtonClicked : HomeEvent()
    object ScreenRefreshed : HomeEvent()
}