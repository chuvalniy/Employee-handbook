package com.example.feature.di

import com.example.feature.presentation.details.view_model.DetailViewModel
import com.example.feature.presentation.home.view_model.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailScreenViewModel = module {
    viewModel {
        DetailViewModel()
    }
}

val homeViewModel = module {
    viewModel {
        HomeViewModel(
            fetchDataUseCase = get(),
        )
    }
}
