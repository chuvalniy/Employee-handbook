package com.example.feature_details.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_data.repository.HomeRepository
import com.example.core_model.DomainUser
import com.example.feature_details.fragment.DetailFragment
import com.example.feature_details.model.DetailsEvent
import com.example.feature_details.model.DetailsSideEffect
import com.example.feature_details.model.DetailsState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


//TODO
class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: HomeRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsState())
    val uiState get() = _uiState.asStateFlow()

    private val _uiEffect = Channel<DetailsSideEffect>()
    val uiEffect get() = _uiEffect.receiveAsFlow()


    init {
        _uiState.value = _uiState.value.copy(
//            data = savedStateHandle.get<DomainUser>(DetailFragment.KEY_USER)!! // TODO
        )
    }

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.BackButtonPressed -> backButtonPressed()
        }
    }

    private fun backButtonPressed() = viewModelScope.launch {
        _uiEffect.send(DetailsSideEffect.NavigateBack)
    }
}