package com.example.core_network.di

import dagger.Component

@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    @Component.Builder
    interface Builder {
        fun build(): NetworkComponent
    }
}