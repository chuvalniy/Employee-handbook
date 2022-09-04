package com.example.core_preferences

import android.content.Context
import com.example.core.utils.SortType
import javax.inject.Inject

private const val SHARED_PREFS_NAME = "user_settings"
private const val KEY_SORT_TYPE = "sort_type"

class UserUserPreferencesImpl @Inject constructor(
    context: Context
) : UserPreferences {

    private val sharedPref = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun updateSortType(sortType: SortType) {
        sharedPref.edit().putString(KEY_SORT_TYPE, sortType.name).apply()
    }

    override fun fetchSortType(): SortType {
        return SortType.valueOf(
            sharedPref.getString(KEY_SORT_TYPE, SortType.BY_NAME.name) ?: SortType.BY_NAME.name
        )
    }
}

