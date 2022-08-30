package com.example.kode_test_app

import android.app.Application
import android.content.Context

class BaseApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .context(context = this)
            .build()
    }
    override fun onCreate() {
        super.onCreate()

    }
}

