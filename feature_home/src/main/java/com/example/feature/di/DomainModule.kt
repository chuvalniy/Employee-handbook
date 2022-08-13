package com.example.feature.di

import com.example.feature.data.repository.HomeRepositoryImpl
import com.example.feature.domain.repository.HomeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<HomeRepository> {
        HomeRepositoryImpl(
            db = get(),
            api = get(),
            sharedPref = get()
        )
    }
}
