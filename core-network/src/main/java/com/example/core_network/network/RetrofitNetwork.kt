package com.example.core_network.network

import com.example.core_network.model.NetworkResponse
import retrofit2.http.GET

interface RetrofitNetwork {

    @GET("users")
    suspend fun fetchCloudDataSource(): NetworkResponse

    companion object {
        const val BASE_URL = "https://stoplight.io/mocks/kode-education/trainee-test/25143926/"
    }
}