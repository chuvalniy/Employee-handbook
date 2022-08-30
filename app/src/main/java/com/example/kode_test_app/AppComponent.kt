package com.example.kode_test_app

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.core.utils.ConnectivityObserver
import com.example.core.utils.NetworkConnectivityObserver
import com.example.feature.di.HomeDeps
import dagger.*
import javax.inject.Scope
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent : HomeDeps {

    override val connectivityObserver: ConnectivityObserver

    override val glide: RequestManager

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}

@Scope
annotation class AppScope

