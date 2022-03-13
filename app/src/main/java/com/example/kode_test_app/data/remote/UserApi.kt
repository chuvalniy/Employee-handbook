package com.example.kode_test_app.data.remote

import com.example.kode_test_app.domain.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface UserApi {

    @GET("users")
    suspend fun getUsers(): UserResponse

    companion object {
        const val BASE_URL = "https://stoplight.io/mocks/kode-education/trainee-test/25143926/"
    }
}