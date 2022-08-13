package com.example.feature.presentation.home.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature.domain.model.DomainDataSource
import com.example.feature.domain.repository.HomeRepository
import com.example.feature.presentation.home.model.SortType
import com.example.feature.presentation.home.model.UiEvent
import com.example.feature.presentation.home.model.UiSideEffect
import com.example.feature.presentation.home.model.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    private val _uiEffect = Channel<UiSideEffect>()
    val uiEffect get() = _uiEffect.receiveAsFlow()

    init {
        _uiState.value = _uiState.value.copy(
            sortType = repository.fetchSortType(),
            departmentFilter = state.get<String>(KEY_FILTER_SAVED_STATE) ?: DEFAULT_VALUE,
            searchQuery = state.get<String>(KEY_SEARCH_SAVED_STATE) ?: DEFAULT_VALUE
        )
        fetchData(fetchFromRemote = true)
    }

    private fun fetchData(
        department: String = _uiState.value.departmentFilter,
        sortType: SortType = _uiState.value.sortType,
        searchQuery: String = _uiState.value.searchQuery,
        fetchFromRemote: Boolean = false
    ) = viewModelScope.launch {
        repository.fetchData(department, sortType, searchQuery, fetchFromRemote).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = result.isLoading)
                }
                is Resource.Success -> {
                    processSuccessResult(result)
                }
                is Resource.Error -> {
                    processErrorResult(result)
                }
            }
        }.launchIn(this)
    }

    private fun processSuccessResult(result: Resource<List<DomainDataSource>>) {
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            isRefreshing = false,
            data = result.data ?: emptyList(),
            error = null
        )
    }

    private fun processErrorResult(result: Resource<List<DomainDataSource>>) {
        _uiState.value = _uiState.value.copy(error = result.error)
        viewModelScope.launch {
            _uiEffect.send(
                UiSideEffect.ShowSnackbar(
                    _uiState.value.error ?: "" // TODO
                )
            )
        }
    }

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.DepartmentSelected -> departmentSelected(event)

            is UiEvent.SearchQueryChanged -> searchQueryChanged(event)

            is UiEvent.SortTypeSelected -> sortTypeSelected(event)

            is UiEvent.UserItemClicked -> userItemClicked(event)

            is UiEvent.FilterButtonClicked -> filterButtonClicked()

            is UiEvent.TryAgainButtonClicked -> tryAgainButtonClicked()

            is UiEvent.ScreenRefreshed -> screenRefreshed()
        }
    }

    private fun screenRefreshed() {
        _uiState.value = _uiState.value.copy(isRefreshing = true)
        viewModelScope.launch { fetchData(fetchFromRemote = true) }
    }

    private fun filterButtonClicked() {
        viewModelScope.launch { _uiEffect.send(UiSideEffect.ShowFilterDialog) }
    }

    private fun userItemClicked(event: UiEvent.UserItemClicked) {
        viewModelScope.launch { _uiEffect.send(UiSideEffect.NavigateToDetails(event.user)) }
    }

    private fun sortTypeSelected(event: UiEvent.SortTypeSelected) {
        repository.updateSortType(event.sortType)
        _uiState.value = _uiState.value.copy(sortType = repository.fetchSortType())
        viewModelScope.launch { fetchData() }
    }

    private fun searchQueryChanged(event: UiEvent.SearchQueryChanged) {
        if (event.query == _uiState.value.searchQuery) return
        state[KEY_SEARCH_SAVED_STATE] = event.query

        _uiState.value = _uiState.value.copy(
            searchQuery = state.get<String>(KEY_SEARCH_SAVED_STATE) ?: DEFAULT_VALUE
        )
        viewModelScope.launch { fetchData() }
    }

    private fun departmentSelected(event: UiEvent.DepartmentSelected) {
        if (_uiState.value.departmentFilter == event.department) return
        state[KEY_FILTER_SAVED_STATE] = event.department

        _uiState.value = _uiState.value.copy(
            departmentFilter = state.get<String>(KEY_FILTER_SAVED_STATE) ?: DEFAULT_VALUE
        )
        viewModelScope.launch { fetchData() }
    }

    private fun tryAgainButtonClicked() {
        viewModelScope.launch { fetchData() }
    }

    private companion object {
        const val KEY_SEARCH_SAVED_STATE = "search"
        const val KEY_FILTER_SAVED_STATE = "filter"
        const val DEFAULT_VALUE = ""
    }
}