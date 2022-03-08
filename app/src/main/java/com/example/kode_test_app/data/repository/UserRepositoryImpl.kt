package com.example.kode_test_app.data.repository

import android.util.Log
import com.example.kode_test_app.data.local.UserDao
import com.example.kode_test_app.data.remote.UserApi
import com.example.kode_test_app.domain.model.User
import com.example.kode_test_app.domain.repository.UserRepository
import com.example.kode_test_app.utils.Resource
import com.example.kode_test_app.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow


class UserRepositoryImpl(
    private val dao: UserDao,
    private val api: UserApi
): UserRepository {

    override fun getUsers(): Flow<Resource<List<User>>> = networkBoundResource(
        query = {
            dao.getUsers()
        },
        fetch = {
            api.getUsers().items
        },
        saveFetchResult = { users ->
            dao.deleteUsers()
            dao.insertUsers(users = users)
        }
    )

}