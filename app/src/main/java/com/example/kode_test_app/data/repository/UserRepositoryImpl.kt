package com.example.kode_test_app.data.repository

import com.example.kode_test_app.data.local.UserDao
import com.example.kode_test_app.data.remote.UserApi
import com.example.kode_test_app.domain.model.User
import com.example.kode_test_app.domain.repository.UserRepository
import com.example.kode_test_app.utils.SortType
import kotlinx.coroutines.flow.Flow


class UserRepositoryImpl(
    private val dao: UserDao,
    private val api: UserApi
) : UserRepository {

    override fun getUsers(department: String, sortType: SortType, searchQuery: String): Flow<List<User>> {
        return dao.getUsers(department, sortType, searchQuery)
    }

    override suspend fun refreshData() {
        val remoteData = api.getUsers().items
        dao.deleteUsers()
        dao.insertUsers(remoteData)
    }

}