package com.example.feature.di

import com.bumptech.glide.RequestManager
import com.example.core.utils.ConnectivityObserver
import dagger.Component
import javax.inject.Scope

@Component(
    dependencies = [HomeDeps::class],
    modules = [HomeModule::class]
)
@HomeScope
interface HomeComponent {

    @Component.Builder
    interface Builder {

        fun deps(homeDeps: HomeDeps): Builder

        fun build(): HomeComponent
    }
}

interface HomeDeps {

    val glide: RequestManager
    val connectivityObserver: ConnectivityObserver
}

@Scope
annotation class HomeScope