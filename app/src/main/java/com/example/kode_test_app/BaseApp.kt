package com.example.kode_test_app

import android.app.Application
import com.example.feature.di.HomeDepsStore
import com.example.kode_test_app.di.AppComponent
import com.example.kode_test_app.di.DaggerAppComponent

class BaseApp : Application() {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .context(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        HomeDepsStore.deps = appComponent
    }
}

