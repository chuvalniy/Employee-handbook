package com.example.core_data.repository

import com.example.core_data.mapper.CacheMapper
import com.example.core_database.database.CacheDataSource
import com.example.core_model.DomainUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val database: CacheDataSource,
    private val cacheMapper: CacheMapper
) : DetailsRepository {

    override fun fetchData(id: String): Flow<DomainUser> {
        val data = database.fetchSingleData(id)
        return data.map { cacheMapper.map(it) }
    }
}