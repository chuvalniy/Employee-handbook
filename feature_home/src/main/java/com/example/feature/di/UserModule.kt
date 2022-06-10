package com.example.feature.di

import android.app.Application
import androidx.room.Room
import com.example.feature.data.local.UserDatabase
import com.example.feature.data.remote.UserApi
import com.example.feature.data.repository.UserRepositoryImpl
import com.example.feature.domain.repository.UserRepository
import com.example.feature.presentation.user_detail.view_model.UserDetailViewModel
import com.example.feature.presentation.user_main.view_model.MainScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val apiModule = module {
    fun provideUserApi() = Retrofit.Builder()
        .baseUrl(UserApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserApi::class.java)

    single {
        provideUserApi()
    }
}

val databaseModule = module {
    fun provideUserDatabase(application: Application) = Room.databaseBuilder(
        application,
        UserDatabase::class.java,
        "user_db"
    ).build()

    fun provideUserDao(database: UserDatabase) = database.dao

    single { provideUserDatabase(androidApplication()) }
    single { provideUserDao(database = get()) }
}

val repositoryModule = module {
    fun provideUserRepository(db: UserDatabase, api: UserApi): UserRepository {
        return UserRepositoryImpl(db, api)
    }

    single { provideUserRepository(db = get(), api = get()) }
}

val detailScreenViewModel = module {
    viewModel {
        UserDetailViewModel(repository = get())
    }
}

val mainScreenViewModel = module {
    viewModel {
        MainScreenViewModel(repository = get())
    }
}

