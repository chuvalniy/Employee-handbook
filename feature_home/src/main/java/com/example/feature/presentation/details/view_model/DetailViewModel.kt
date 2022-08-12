package com.example.feature.presentation.details.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.feature.domain.model.DomainDataSource
import com.example.feature.presentation.details.model.DetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsState())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(
            data = savedStateHandle.get<DomainDataSource>("user")!!
        )
    }
}