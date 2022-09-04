package com.example.feature_details.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.presentation.BaseViewModel
import com.example.core_data.repository.DetailRepository
import com.example.feature_details.model.DetailEvent
import com.example.feature_details.model.DetailSideEffect
import com.example.feature_details.model.DetailState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailViewModel @Inject constructor(
    private val repository: DetailRepository,
    private val savedState: SavedStateHandle
) : BaseViewModel<DetailEvent, DetailState, DetailSideEffect>(DetailState()) {

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        repository.fetchData(id = savedState.get<String>("id") ?: "").onEach { data ->
            _state.update { it.copy(data = data) }
        }.launchIn(this)
    }


    override fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.BackButtonPressed -> backButtonPressed()
        }
    }

    private fun backButtonPressed() = viewModelScope.launch {
        _sideEffect.send(DetailSideEffect.NavigateBack)
    }
}