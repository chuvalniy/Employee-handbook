package com.example.feature_details.di

import androidx.lifecycle.ViewModel

internal class DetailComponentViewModel : ViewModel() {

    val detailsComponent by lazy {
        DaggerDetailsComponent.builder()
            .deps(DetailsDepsProvider.deps)
            .build()
    }

}