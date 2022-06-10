package com.example.feature.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.example.core.utils.networkBoundResource
import com.example.feature.data.local.UserDatabase
import com.example.feature.data.remote.UserApi
import com.example.feature.domain.model.User
import com.example.feature.domain.repository.UserRepository
import com.example.feature.presentation.utils.SortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val db: UserDatabase,
    private val api: UserApi
) : UserRepository {

    private val userDao = db.dao

    override fun getUsers(
        department: String,
        sortType: SortType,
        searchQuery: String
    ) = networkBoundResource(
        query = {
            userDao.getUsers(department, sortType, searchQuery).map { it.map { user -> user.toUser() } }
        },
        fetch = {
            api.getUsers()
        },
        saveFetchResult = { remoteUsers ->
            val cacheUsers = remoteUsers.items.map { it.toUserEntity() }
            db.withTransaction {
                userDao.deleteUsers()
                userDao.insertUsers(cacheUsers)
            }
        }
    )


    override fun getUserById(id: String): Flow<User> {
        return userDao.getUserById(id).map { user ->
            user.toUser()
        }
    }

}