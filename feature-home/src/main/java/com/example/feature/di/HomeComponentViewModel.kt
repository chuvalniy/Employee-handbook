package com.example.feature.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

internal class HomeComponentViewModel(application: Application) : AndroidViewModel(application) {

    val homeComponent: HomeComponent by lazy {
        DaggerHomeComponent.builder()
            .deps(HomeDepsProvider.deps)
            .build()
    }
}