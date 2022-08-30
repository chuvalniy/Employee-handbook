package com.example.feature.data.repository

import androidx.room.withTransaction
import com.bumptech.glide.load.HttpException
import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.feature.R
import com.example.feature.data.local.cache.HomeDatabase
import com.example.feature.data.local.settings.UserPreferences
import com.example.feature.data.mapper.toCacheDataSource
import com.example.feature.data.mapper.toDomainDataSource
import com.example.feature.data.remote.HomeApi
import com.example.feature.domain.model.DomainDataSource
import com.example.feature.domain.repository.HomeRepository
import com.example.feature.presentation.home.model.SortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val db: HomeDatabase,
    private val api: HomeApi,
) : HomeRepository {

    private val dao = db.dao

    override fun fetchData(
        department: String,
        sortType: SortType,
        searchQuery: String,
    ): Flow<Resource<List<DomainDataSource>>> {

    }

}