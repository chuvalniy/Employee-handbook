package com.example.feature.view_model

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
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
    @Assisted private val owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = HomeViewModel(repository, preferences, connectivityObserver, handle) as T
}

@AssistedFactory
interface AssistedHomeViewModelFactory {
    fun create(owner: SavedStateRegistryOwner): HomeViewModelFactory
}

