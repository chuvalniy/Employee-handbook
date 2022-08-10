package com.example.feature.presentation.home.view_model

sealed class UiEvent {
    data class SortTypeSelected(val sortType: SortType): UiEvent()
    data class SearchQueryChanged(val query: String): UiEvent()
    data class FilterSelected(val filterType: String): UiEvent()
    data class UserItemClicked(val id: String): UiEvent()
    object FilterButtonClicked : UiEvent()
}