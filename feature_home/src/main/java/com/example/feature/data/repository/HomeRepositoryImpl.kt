package com.example.feature.data.repository

import androidx.room.withTransaction
import com.example.core.utils.networkBoundResource
import com.example.feature.data.local.HomeDatabase
import com.example.feature.data.remote.HomeApi
import com.example.feature.domain.repository.UserRepository
import com.example.feature.presentation.home.view_model.SortType
import kotlinx.coroutines.flow.map

class HomeRepositoryImpl(
    private val db: HomeDatabase,
    private val api: HomeApi
) : UserRepository {

    private val userDao = db.dao

    override fun getUsers(
        department: String,
        sortType: SortType,
        searchQuery: String
    ) = networkBoundResource(
        query = {
            userDao.fetchCache(department, sortType, searchQuery)
                .map { it.map { user -> user.toUser() } }
        },
        fetch = {
            api.getUsers()
        },
        saveFetchResult = { remoteUsers ->
            val cacheUsers = remoteUsers.items.map { it.toUserEntity() }
            db.withTransaction {
                userDao.clearCache()
                userDao.insertCache(cacheUsers)
            }
        }
    )
}