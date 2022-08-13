package com.example.feature.presentation.details.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.domain.model.DomainDataSource
import com.example.feature.presentation.details.fragment.DetailFragment
import com.example.feature.presentation.details.model.DetailsState
import com.example.feature.presentation.details.model.UiEvent
import com.example.feature.presentation.details.model.UiSideEffect
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsState())
    val uiState get() = _uiState.asStateFlow()

    private val _uiEffect = Channel<UiSideEffect>()
    val uiEffect get() = _uiEffect.receiveAsFlow()


    init {
        _uiState.value = _uiState.value.copy(
            data = savedStateHandle.get<DomainDataSource>(DetailFragment.KEY_USER)!!
        )
    }

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.BackButtonPressed -> backButtonPressed()
        }
    }

    private fun backButtonPressed() = viewModelScope.launch {
        _uiEffect.send(UiSideEffect.NavigateBack)
    }
}