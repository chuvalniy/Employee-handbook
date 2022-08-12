package com.example.feature.presentation.home.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature.domain.model.DomainDataSource
import com.example.feature.domain.use_case.FetchDataUseCase
import com.example.feature.presentation.home.model.UiEvent
import com.example.feature.presentation.home.model.UiSideEffect
import com.example.feature.presentation.home.model.UiState
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
        department: String = _uiState.value.departmentFilter,
        sortType: SortType = _uiState.value.sortType,
        searchQuery: String = _uiState.value.searchQuery,
        refreshData: Boolean = _uiState.value.isRefreshing
    ) = viewModelScope.launch {
        fetchDataUseCase(department, sortType, searchQuery, refreshData).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    processErrorResult(result)
                }
                is Resource.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = result.isLoading)
                }
                is Resource.Success -> {
                    processSuccessResult(result)
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
                    _uiState.value.error ?: ""
                )
            )
        }
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
            is UiEvent.TryAgainButtonClicked -> {
                viewModelScope.launch { fetchData() }
            }
            is UiEvent.ScreenRefreshed -> {
                Log.d("TAGTAG", "${_uiState.value.isRefreshing}")
                _uiState.value = _uiState.value.copy(isRefreshing = true)
                viewModelScope.launch { fetchData() }
            }
        }
    }
}