package com.example.kode_test_app.di

import android.app.Application
import androidx.room.Room
import com.example.kode_test_app.data.local.UserDatabase
import com.example.kode_test_app.data.remote.UserApi
import com.example.kode_test_app.data.repository.UserRepositoryImpl
import com.example.kode_test_app.domain.model.User
import com.example.kode_test_app.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object UserModule {

    @Singleton
    @Provides
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            "user_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl(UserApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRepository(db: UserDatabase, api: UserApi): UserRepository {
        return UserRepositoryImpl(db.dao, api)
    }
}