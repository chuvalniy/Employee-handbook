package com.example.kode_test_app.presentation.user_detail

import androidx.lifecycle.*
import com.example.kode_test_app.domain.model.User
import com.example.kode_test_app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    fun getUser(id: String): LiveData<User> {
        return repository.getUserById(id).asLiveData()
    }
}