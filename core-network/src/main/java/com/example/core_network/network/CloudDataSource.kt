package com.example.core_network.network

import com.example.core_network.model.NetworkUser

interface CloudDataSource {

    suspend fun fetchData(): List<NetworkUser>

    class RetrofitCloudDataSource(
        private val api: RetrofitNetwork
    ) : CloudDataSource {

        override suspend fun fetchData(): List<NetworkUser> = api.fetchCloudDataSource().items
    }
}