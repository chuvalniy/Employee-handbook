package com.example.feature.presentation.home.model

import com.example.feature.domain.model.DomainDataSource
import com.example.feature.presentation.home.view_model.SortType

sealed class UiEvent {
    data class SortTypeSelected(val sortType: SortType): UiEvent()
    data class SearchQueryChanged(val query: String): UiEvent()
    data class DepartmentSelected(val department: String): UiEvent()
    data class UserItemClicked(val user: DomainDataSource): UiEvent()
    object FilterButtonClicked : UiEvent()
    object TryAgainButtonClicked : UiEvent()
    object ScreenRefreshed : UiEvent()
}