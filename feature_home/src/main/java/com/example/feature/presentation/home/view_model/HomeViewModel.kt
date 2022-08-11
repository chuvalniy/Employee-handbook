package com.example.feature.presentation.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature.domain.use_case.FetchDataUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
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
        department: String = _uiState.value.departmentFilter,
        sortType: SortType = _uiState.value.sortType,
        searchQuery: String = _uiState.value.searchQuery
    ) = viewModelScope.launch {
        fetchDataUseCase(department, sortType, searchQuery).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
                is Resource.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = result.isLoading)
                }
                is Resource.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        data = result.data ?: emptyList()
                    )
                }
            }
        }.launchIn(this)
    }


    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.DepartmentSelected -> {
                if (_uiState.value.departmentFilter == event.department) return

                _uiState.value = _uiState.value.copy(departmentFilter = event.department)
                viewModelScope.launch { fetchData() }
            }
            is UiEvent.SearchQueryChanged -> {
                _uiState.value = _uiState.value.copy(searchQuery = event.query)
                viewModelScope.launch { fetchData() }

            }
            is UiEvent.SortTypeSelected -> {
                _uiState.value = _uiState.value.copy(sortType = event.sortType)
                viewModelScope.launch { fetchData() }
            }
            is UiEvent.UserItemClicked -> {
                viewModelScope.launch { _uiEffect.send(UiSideEffect.NavigateToDetails(event.user)) }
            }
            is UiEvent.FilterButtonClicked -> {
                viewModelScope.launch { _uiEffect.send(UiSideEffect.ShowFilterDialog) }
            }
        }
    }
}