package com.example.core_preferences

import com.example.core.utils.SortType

interface UserPreferences {

    fun updateSortType(sortType: SortType)

    fun fetchSortType(): SortType
}