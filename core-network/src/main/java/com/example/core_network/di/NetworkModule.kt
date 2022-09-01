package com.example.core_network.di

import com.example.core_network.network.NetworkRetrofit
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
object NetworkModule {

    @Provides
    fun provideNetworkRetrofit(): NetworkRetrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkRetrofit.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}