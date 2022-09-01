package com.example.feature.view_model

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.core.core.ConnectivityObserver
import com.example.core.core.UiText
import com.example.core.presentation.BaseViewModel
import com.example.core.utils.SortType
import com.example.core.utils.onEachResource
import com.example.core_data.repository.HomeRepository
import com.example.core_preferences.UserPreferences
import com.example.feature.model.HomeEffect
import com.example.feature.model.HomeEvent
import com.example.feature.model.HomeState
import com.example.feature.model.LoadingState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

// return SavedStateHandle

internal class HomeViewModel(
    private val repository: HomeRepository,
    private val preferences: UserPreferences,
    private val connectivityObserver: ConnectivityObserver,
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>(HomeState()) {

    init {
        if (!connectivityObserver.hasInternetConnection()) navigateToError()
        else {
            _state.value = _state.value.copy(
                sortType = preferences.fetchSortType(),
//                departmentFilter = savedState.get<String>(KEY_FILTER_SAVED_STATE)
//                    ?: DEFAULT_VALUE,
//                searchQuery = savedState.get<String>(KEY_SEARCH_SAVED_STATE) ?: DEFAULT_VALUE
            )
            fetchData(loadingState = LoadingState.SHIMMER)
        }
    }

    private fun fetchData(
        department: String = _state.value.departmentFilter,
        sortType: SortType = _state.value.sortType,
        searchQuery: String = _state.value.searchQuery,
        loadingState: LoadingState = LoadingState.SNACKBAR,
    ) = viewModelScope.launch {
        repository.fetchData(department, sortType, searchQuery).onEachResource(
            onError = { showSnackbar(it) },
            onSuccess = {
                _state.value = _state.value.copy(data = it) },
            onLoading = { isLoading ->
                if (isLoading) _state.value = _state.value.copy(loadingState = loadingState)
                else _state.value = _state.value.copy(loadingState = LoadingState.NONE)
            }
        ).launchIn(this)
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.DepartmentSelected -> departmentSelected(event)

            is HomeEvent.SearchQueryChanged -> searchQueryChanged(event)

            is HomeEvent.SortTypeSelected -> sortTypeSelected(event)

            is HomeEvent.UserItemClicked -> userItemClicked(event)

            is HomeEvent.FilterButtonClicked -> filterButtonClicked()

            is HomeEvent.ScreenRefreshed -> screenRefreshed()
        }
    }

    private fun screenRefreshed() {
        _state.value = _state.value.copy(isRefreshing = false)
        fetchData()
    }

    private fun filterButtonClicked() {
        viewModelScope.launch { _sideEffect.send(HomeEffect.ShowFilterDialog) }
    }

    private fun userItemClicked(event: HomeEvent.UserItemClicked) {
        viewModelScope.launch { _sideEffect.send(HomeEffect.NavigateToDetails(event.user)) }
    }

    private fun sortTypeSelected(event: HomeEvent.SortTypeSelected) {
        preferences.updateSortType(event.sortType)
        _state.value = _state.value.copy(sortType = preferences.fetchSortType())
        fetchData()
    }

    private var searchJob: Job? = null;

    private fun searchQueryChanged(event: HomeEvent.SearchQueryChanged) {
        if (event.query == _state.value.searchQuery) return
//        savedState[KEY_SEARCH_SAVED_STATE] = event.query

        _state.value = _state.value.copy(
//            searchQuery = savedState.get<String>(KEY_SEARCH_SAVED_STATE) ?: DEFAULT_VALUE
            searchQuery = event.query
        )

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(400)
            fetchData()
        }
    }

    private fun departmentSelected(event: HomeEvent.DepartmentSelected) {
        if (_state.value.departmentFilter == event.department) return
//        savedState[KEY_FILTER_SAVED_STATE] = event.department

        _state.value = _state.value.copy(
//            departmentFilter = savedState.get<String>(KEY_FILTER_SAVED_STATE) ?: DEFAULT_VALUE
            departmentFilter = event.department
        )
        fetchData()
    }

    private fun navigateToError() = viewModelScope.launch {
        _sideEffect.send(HomeEffect.NavigateToErrorScreen)
    }

    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _sideEffect.send(HomeEffect.ShowSnackbar(message))
    }

    private companion object {
        const val KEY_SEARCH_SAVED_STATE = "search"
        const val KEY_FILTER_SAVED_STATE = "filter"
        const val DEFAULT_VALUE = ""
    }


}