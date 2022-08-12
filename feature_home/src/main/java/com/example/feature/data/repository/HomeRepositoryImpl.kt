package com.example.feature.data.repository

import androidx.room.withTransaction
import com.example.core.utils.Resource
import com.example.feature.data.local.HomeDatabase
import com.example.feature.data.remote.HomeApi
import com.example.feature.domain.model.DomainDataSource
import com.example.feature.domain.repository.UserRepository
import com.example.feature.presentation.home.view_model.SortType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val db: HomeDatabase,
    private val api: HomeApi
) : UserRepository {

    private val dao = db.dao

    override fun fetchUsers(
        department: String,
        sortType: SortType,
        searchQuery: String,
        refreshData: Boolean
    ): Flow<Resource<List<DomainDataSource>>> = flow {
        emit(Resource.Loading(isLoading = true))

        val cache = dao.fetchCache(department, sortType, searchQuery)

        if (cache.isNotEmpty()) {
            emit(Resource.Success(cache.map { it.toDomainDataSource() }))
            emit(Resource.Loading(isLoading = false))
        }

        val response = try {
            api.fetchCloudDataSource()
        } catch (e: Exception) {
            emit(Resource.Error(error = e.message))
            null
        }

        response?.let { data ->
            db.withTransaction {
                dao.clearCache()
                dao.insertCache(data.items.map { it.toCacheDataSource() })
            }

            emit(
                Resource.Success(
                    dao.fetchCache(department, sortType, searchQuery)
                        .map { it.toDomainDataSource() })
            )
            emit(Resource.Loading(isLoading = false))
        }
    }
}