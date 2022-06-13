package com.example.feature.di

import com.example.feature.data.local.UserDatabase
import com.example.feature.data.remote.UserApi
import com.example.feature.data.repository.UserRepositoryImpl
import com.example.feature.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    fun provideUserRepository(db: UserDatabase, api: UserApi): UserRepository {
        return UserRepositoryImpl(db, api)
    }

    single { provideUserRepository(db = get(), api = get()) }
}
