package com.example.feature.data.local.settings

import android.content.Context
import com.example.feature.presentation.home.view_model.SortType

private const val SHARED_PREFS_NAME = "user_settings"
private const val KEY_FILTER = "department_filter"
private const val KEY_SORT_TYPE = "sort_type"

class PreferencesManagerImpl(
    context: Context
) : PreferencesManager {

    private val sharedPref = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun updateSortType(sortType: SortType) {
        sharedPref.edit().putString(KEY_SORT_TYPE, sortType.name).apply()
    }

    override fun updateFilter(filter: String) {
        sharedPref.edit().putString(KEY_FILTER, filter).apply()
    }

    override fun fetchFilter(): String {
        return sharedPref.getString(KEY_FILTER, "") ?: ""
    }

    override fun fetchSortType(): SortType {
        return SortType.valueOf(
            sharedPref.getString(KEY_SORT_TYPE, SortType.BY_NAME.name) ?: SortType.BY_NAME.name
        )
    }
}

