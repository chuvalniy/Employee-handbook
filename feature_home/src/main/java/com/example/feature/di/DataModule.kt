package com.example.feature.di

import android.app.Application
import androidx.room.Room
import com.example.feature.data.local.HomeDatabase
import com.example.feature.data.remote.HomeApi
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val apiModule = module {
    fun provideUserApi() = Retrofit.Builder()
        .baseUrl(HomeApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HomeApi::class.java)

    single {
        provideUserApi()
    }
}

val databaseModule = module {
    fun provideUserDatabase(application: Application) = Room.databaseBuilder(
        application,
        HomeDatabase::class.java,
        "user_db"
    ).build()

    fun provideUserDao(database: HomeDatabase) = database.dao

    single { provideUserDatabase(androidApplication()) }
    single { provideUserDao(database = get()) }
}




