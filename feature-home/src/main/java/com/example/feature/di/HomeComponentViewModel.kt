package com.example.feature.di

import androidx.lifecycle.ViewModel

internal class HomeComponentViewModel : ViewModel() {

    val homeComponent: HomeComponent by lazy {
        DaggerHomeComponent.builder()
            .deps(HomeDepsProvider.deps)
            .build()
    }
}