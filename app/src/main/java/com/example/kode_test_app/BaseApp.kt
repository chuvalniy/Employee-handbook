package com.example.kode_test_app

import android.app.Application
import com.example.feature.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApp)
            modules(
                sharedPreferencesModule,
                apiModule,
                databaseModule,
                repositoryModule,
                homeViewModel,
                detailScreenViewModel,
                coreModule,
                useCaseModule
            )
        }
    }
}