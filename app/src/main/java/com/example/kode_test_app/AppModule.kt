package com.example.kode_test_app

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.core.utils.ConnectivityObserver
import com.example.core.utils.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRequestManager(context: Context): RequestManager {
        return Glide.with(context).setDefaultRequestOptions(
            RequestOptions()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(context: Context): ConnectivityObserver {
        return NetworkConnectivityObserver(context)
    }
}