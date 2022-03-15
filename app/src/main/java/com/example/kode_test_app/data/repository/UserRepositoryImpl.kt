package com.example.kode_test_app.data.repository

import com.example.kode_test_app.data.local.UserDao
import com.example.kode_test_app.data.remote.UserApi
import com.example.kode_test_app.domain.model.User
import com.example.kode_test_app.domain.repository.UserRepository
import com.example.kode_test_app.core.utils.SortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserRepositoryImpl(
    private val dao: UserDao,
    private val api: UserApi
) : UserRepository {

    override fun getUsers(department: String, sortType: SortType, searchQuery: String): Flow<List<User>> {
        return dao.getUsers(department, sortType, searchQuery).map { users ->
            users.map { it.toUser() }
        }
    }

    override suspend fun refreshData() {
        try {
            val remoteData = api.getUsers().items.map { it.toUserEntity() }
            dao.deleteUsers()
            dao.insertUsers(remoteData)
        } catch (e: Exception) {

        }
    }

    override fun getUserById(id: String): Flow<User> {
        return dao.getUserById(id).map { user ->
            user.toUser()
        }
    }

}