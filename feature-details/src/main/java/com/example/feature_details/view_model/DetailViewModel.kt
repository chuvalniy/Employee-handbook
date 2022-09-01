package com.example.feature_details.view_model

import androidx.lifecycle.viewModelScope
import com.example.core.presentation.BaseViewModel
import com.example.core_data.repository.HomeRepository
import com.example.feature_details.model.DetailsEvent
import com.example.feature_details.model.DetailsSideEffect
import com.example.feature_details.model.DetailsState
import kotlinx.coroutines.launch


//TODO
class DetailViewModel(
    private val repository: HomeRepository,
) : BaseViewModel<DetailsEvent, DetailsState, DetailsSideEffect>(DetailsState()) {


    init {
//        _uiState.value = _uiState.value.copy(
//            data = savedStateHandle.get<DomainUser>(DetailFragment.KEY_USER)!! // TODO
//        )
    }

    private fun fetchData() {
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