package com.example.feature.presentation.user_detail.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.feature.domain.model.User
import com.example.feature.domain.repository.UserRepository
import com.example.feature.domain.use_case.GetUserByIdUseCase

class UserDetailViewModel(
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {

    fun getUser(id: String): LiveData<User> {
        return getUserByIdUseCase(id).asLiveData()
    }
}