package com.example.feature.di

import com.example.feature.data.repository.HomeRepositoryImpl
import com.example.feature.domain.repository.HomeRepository
import com.example.feature.domain.use_case.*
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

val useCaseModule = module {
    factory {
        FetchDataUseCase(repository = get())
    }

    factory {
        FetchFilterUseCase(repository = get())
    }

    factory {
        FetchSortTypeUseCase(repository = get())
    }

    factory {
        UpdateFilterUseCase(repository = get())
    }

    factory {
        UpdateSortTypeUseCase(repository = get())
    }

}