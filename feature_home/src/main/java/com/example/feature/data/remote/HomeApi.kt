package com.example.feature.data.remote

import com.example.feature.data.remote.dto.UserResponse
import retrofit2.http.GET

interface HomeApi {

    @GET("users")
    suspend fun getUsers(): UserResponse

    companion object {
        const val BASE_URL = "https://stoplight.io/mocks/kode-education/trainee-test/25143926/"
    }
}