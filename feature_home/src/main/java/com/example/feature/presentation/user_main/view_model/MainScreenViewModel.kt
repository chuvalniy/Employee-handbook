package com.example.feature.presentation.user_main.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.feature.domain.use_case.GetUsersUseCase
import com.example.feature.presentation.utils.SortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    val filterType = MutableStateFlow("")
    val sortType = MutableStateFlow(SortType.BY_NAME)
    val queryText = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    private val sortFlow = combine(
        filterType,
        sortType,
        queryText
    ) { filterType, sortType, queryText ->
        Triple(filterType, sortType, queryText)
    }
        .flatMapLatest { (filterType, sortType, queryText) ->
            getUsersUseCase(filterType, sortType, queryText)
    }

    val data = sortFlow.asLiveData()
}