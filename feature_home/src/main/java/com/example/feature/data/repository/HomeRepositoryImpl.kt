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
        return flow {
            emit(Resource.Loading(isLoading = true))

            val cache = dao.fetchCache(department, sortType, searchQuery)
            emit(Resource.Success(cache.map { it.toDomainDataSource() }))

            val response = try {
                api.fetchCloudDataSource()
            } catch (e: HttpException) {
                emit(Resource.Error(error = UiText.StringResource(R.string.api_error)))
                emit(Resource.Loading(isLoading = false))
                null
            } catch (e: IOException) {
                emit(Resource.Error(error = UiText.StringResource(R.string.api_error)))
                emit(Resource.Loading(isLoading = false))
                null
            } catch (e: Exception) {
                emit(Resource.Error(error = UiText.StringResource(com.example.core.R.string.unexpected_error)))
                emit(Resource.Loading(isLoading = false))
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

}