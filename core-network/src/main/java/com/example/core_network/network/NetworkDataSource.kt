package com.example.core_network.network

import com.example.core_network.model.NetworkUser

interface NetworkDataSource {

    suspend fun fetchData(): List<NetworkUser>

    class RetrofitNetworkDataSource(
        private val api: RetrofitNetwork
    ) : NetworkDataSource {

        override suspend fun fetchData(): List<NetworkUser> = api.fetchCloudDataSource().items
    }
}