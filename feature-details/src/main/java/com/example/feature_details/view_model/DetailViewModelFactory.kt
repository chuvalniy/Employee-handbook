package com.example.feature_details.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_data.repository.HomeRepository
import javax.inject.Inject

class DetailViewModelFactory @Inject constructor(
    private val repository: HomeRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == DetailViewModel::class.java)
        return DetailViewModel(repository) as T
    }
}