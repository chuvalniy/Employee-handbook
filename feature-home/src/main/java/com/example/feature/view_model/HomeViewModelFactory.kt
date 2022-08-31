package com.example.feature.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.core.ConnectivityObserver
import com.example.core_data.repository.HomeRepository
import com.example.core_preferences.UserPreferences
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class HomeViewModelFactory @AssistedInject constructor(
    private val repository: HomeRepository,
    private val preferences: UserPreferences,
    private val connectivityObserver: ConnectivityObserver,
    @Assisted private val savedState: SavedStateHandle
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == HomeViewModel::class)
        return HomeViewModel(repository, preferences, connectivityObserver, savedState) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted savedState: SavedStateHandle): HomeViewModelFactory
    }
}