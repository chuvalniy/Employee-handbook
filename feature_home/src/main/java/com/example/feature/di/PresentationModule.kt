package com.example.feature.di

import com.example.feature.domain.use_case.GetUserByIdUseCase
import com.example.feature.domain.use_case.GetUsersUseCase
import com.example.feature.presentation.user_detail.view_model.UserDetailViewModel
import com.example.feature.presentation.user_main.view_model.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailScreenViewModel = module {
    viewModel {
        UserDetailViewModel(getUserByIdUseCase = get())
    }
}

val mainScreenViewModel = module {
    viewModel {
        MainScreenViewModel(getUsersUseCase = get())
    }
}

val useCaseModule = module {
    factory {
        GetUsersUseCase(get())
    }

    factory {
        GetUserByIdUseCase(get())
    }
}