package com.example.feature.presentation.home.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.utils.ConnectivityObserver
import com.example.feature.data.local.settings.UserPreferences
import com.example.feature.domain.repository.HomeRepository
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