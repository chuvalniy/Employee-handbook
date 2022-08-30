package com.example.core_data.repository

import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.core.utils.SortType
import com.example.core_database.database.CacheDataSource
import com.example.core_model.DomainUser
import com.example.core_network.network.CloudDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
) : HomeRepository {

    override fun fetchData(
        department: String,
        sortType: SortType,
        searchQuery: String
    ): Flow<Resource<List<DomainUser>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val cache = cacheDataSource.fetchCache(department, sortType, searchQuery)
            emit(Resource.Success(cache.map { it.toDomainDataSource() }))

            val response = try {
                cloudDataSource.fetchData()
            } catch (e: Exception) {
                emit(Resource.Error(error = UiText.StringResource(com.example.core.R.string.unexpected_error))) // TODO
                null
            }

            response?.let { data ->
                cacheDataSource.updateData() // TODo

                emit(
                    Resource.Success(
                        cacheDataSource.fetchCache(department, sortType, searchQuery)
                            .map { it.toDomainDataSource() })
                )
                emit(Resource.Loading(isLoading = false))
            }
        }
    }
}