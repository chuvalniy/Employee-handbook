package com.example.core_data.repository

import com.example.core.core.UiText
import com.example.core.core.Resource
import com.example.core.utils.SortType
import com.example.core_data.mapper.CacheMapper
import com.example.core_data.mapper.NetworkMapper
import com.example.core_database.database.CacheDataSource
import com.example.core_model.DomainUser
import com.example.core_network.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val cacheDataSource: CacheDataSource,
    private val cacheMapper: CacheMapper,
    private val networkDataSource: NetworkDataSource,
    private val networkMapper: NetworkMapper,
) : HomeRepository {

    override fun fetchData(
        department: String,
        sortType: SortType,
        searchQuery: String
    ): Flow<Resource<List<DomainUser>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val cache = cacheDataSource.fetchCache(department, sortType, searchQuery)
            emit(Resource.Success(data = cache.map { cacheMapper.map(it) }))

            val response = try {
                networkDataSource.fetchData()
            } catch (e: Exception) {
                emit(Resource.Error(error = UiText.StringResource(com.example.core.R.string.unexpected_error))) // TODO
                null
            }

            response?.let { data ->
                cacheDataSource.updateData(data.map { networkMapper.map(it) })

                emit(
                    Resource.Success(
                        cacheDataSource.fetchCache(department, sortType, searchQuery)
                            .map { cacheMapper.map(it) })
                )
                emit(Resource.Loading(isLoading = false))
            }
        }
    }
}