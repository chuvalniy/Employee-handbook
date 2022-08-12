package com.example.feature.data.local.settings

import com.example.feature.presentation.home.view_model.SortType

interface PreferencesManager {

    fun updateSortType(sortType: SortType)

    fun updateFilter(filter: String)

    fun fetchFilter(): String

    fun fetchSortType(): SortType
}