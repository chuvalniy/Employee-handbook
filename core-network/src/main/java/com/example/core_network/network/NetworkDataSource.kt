package com.example.core_network.network

import com.example.core_network.model.NetworkUser
import javax.inject.Inject

interface NetworkDataSource {

    suspend fun fetchData(): List<NetworkUser>

    class RetrofitNetworkDataSource @Inject constructor(
        private val api: NetworkRetrofit
    ) : NetworkDataSource {

        override suspend fun fetchData(): List<NetworkUser> = api.fetchCloudDataSource().items
    }
}