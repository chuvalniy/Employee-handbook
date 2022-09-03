package com.example.feature_details.view_model

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.core_data.repository.DetailRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DetailViewModelFactory @AssistedInject constructor(
    private val repository: DetailRepository,
    @Assisted owner: SavedStateRegistryOwner,
    @Assisted defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = DetailViewModel(repository, handle) as T
}

@AssistedFactory
interface AssistedDetailsViewModelFactory {
    fun create(owner: SavedStateRegistryOwner, defaultArgs: Bundle? = null): DetailViewModelFactory
}

