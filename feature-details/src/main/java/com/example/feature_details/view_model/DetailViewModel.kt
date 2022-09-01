package com.example.feature_details.view_model

import androidx.lifecycle.viewModelScope
import com.example.core.presentation.BaseViewModel
import com.example.core_data.repository.DetailsRepository
import com.example.feature_details.model.DetailsEvent
import com.example.feature_details.model.DetailsSideEffect
import com.example.feature_details.model.DetailsState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class DetailViewModel(
    private val repository: DetailsRepository,
) : BaseViewModel<DetailsEvent, DetailsState, DetailsSideEffect>(DetailsState()) {

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        repository.fetchData(id = "asd").onEach { data ->
            _state.update { it.copy(data = data) }
        }.launchIn(this)
    }


    override fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.BackButtonPressed -> backButtonPressed()
        }
    }

    private fun backButtonPressed() = viewModelScope.launch {
        _sideEffect.send(DetailsSideEffect.NavigateBack)
    }
}