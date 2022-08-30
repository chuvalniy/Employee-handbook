package com.example.feature.data.local.settings

import com.example.feature.presentation.home.model.SortType

interface UserPreferences {

    fun updateSortType(sortType: SortType)

    fun fetchSortType(): SortType
}