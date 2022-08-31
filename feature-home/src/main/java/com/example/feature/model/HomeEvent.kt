package com.example.feature.model

import com.example.core.presentation.UiEvent
import com.example.core.utils.SortType
import com.example.core_model.DomainUser

sealed class HomeEvent : UiEvent {
    data class SortTypeSelected(val sortType: SortType) : HomeEvent()
    data class SearchQueryChanged(val query: String) : HomeEvent()
    data class DepartmentSelected(val department: String) : HomeEvent()
    data class UserItemClicked(val user: DomainUser) : HomeEvent()
    object FilterButtonClicked : HomeEvent()
    object ScreenRefreshed : HomeEvent()
}