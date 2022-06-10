package com.example.feature.presentation.user_main.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.feature.presentation.utils.SortType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val repository: com.example.feature.domain.repository.UserRepository
) : ViewModel() {

    val filterType = MutableStateFlow("")
    val sortType = MutableStateFlow(SortType.BY_NAME)
    val queryText = MutableStateFlow("")

    private val sortFlow = combine(
        filterType,
        sortType,
        queryText
    ) { filterType, sortType, queryText ->
        Triple(filterType, sortType, queryText)
    }.flatMapLatest { (filterType, sortType, queryText) ->
        repository.getUsers(filterType, sortType, queryText)
    }

    val data = sortFlow.asLiveData()
}