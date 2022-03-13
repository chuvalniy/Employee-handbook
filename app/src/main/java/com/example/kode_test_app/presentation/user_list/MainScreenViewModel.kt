package com.example.kode_test_app.presentation.user_list

import androidx.lifecycle.*
import com.example.kode_test_app.domain.model.User
import com.example.kode_test_app.domain.repository.UserRepository
import com.example.kode_test_app.utils.Resource
import com.example.kode_test_app.utils.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/* This ViewModel also used in UserListFragment to observe data and set filterType */

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: UserRepository
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

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                repository.refreshData()
            } catch (e: Exception) {

            }
        }
    }
}