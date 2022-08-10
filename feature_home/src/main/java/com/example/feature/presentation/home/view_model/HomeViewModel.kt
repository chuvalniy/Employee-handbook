package com.example.feature.presentation.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature.domain.use_case.FetchDataUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchDataUseCase: FetchDataUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    private val _uiEffect = Channel<UiSideEffect>()
    val uiEffect get() = _uiEffect.receiveAsFlow()

    init {
        fetchData()
    }

    private fun fetchData(
        department: String = _uiState.value.filterType,
        sortType: SortType = _uiState.value.sortType,
        searchQuery: String = _uiState.value.searchQuery
    ) = viewModelScope.launch {
        fetchDataUseCase(department, sortType, searchQuery).onEach { result ->
            when (result) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {

                }
            }
        }.launchIn(this)
    }

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.FilterSelected -> {
                _uiState.value = _uiState.value.copy(filterType = event.filterType)
            }
            is UiEvent.SearchQueryChanged -> {
                _uiState.value = _uiState.value.copy(searchQuery = event.query)
            }
            is UiEvent.SortTypeSelected -> {
                _uiState.value = _uiState.value.copy(sortType = event.sortType)
            }
            is UiEvent.UserItemClicked -> {
                viewModelScope.launch { _uiEffect.send(UiSideEffect.NavigateToDetails(event.id)) }
            }
            is UiEvent.FilterButtonClicked -> {
                viewModelScope.launch { _uiEffect.send(UiSideEffect.ShowFilterDialog) }
            }
        }
    }
}