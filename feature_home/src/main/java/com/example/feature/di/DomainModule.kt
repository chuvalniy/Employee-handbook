package com.example.feature.di

import com.example.feature.data.local.HomeDatabase
import com.example.feature.data.remote.HomeApi
import com.example.feature.data.repository.HomeRepositoryImpl
import com.example.feature.domain.repository.UserRepository
import com.example.feature.domain.use_case.FetchDataUseCase
import org.koin.dsl.module

val repositoryModule = module {
    fun provideUserRepository(db: HomeDatabase, api: HomeApi): UserRepository {
        return HomeRepositoryImpl(db, api)
    }

    single { provideUserRepository(db = get(), api = get()) }
}

val useCaseModule = module {
    factory {
        FetchDataUseCase(repository = get())
    }
}