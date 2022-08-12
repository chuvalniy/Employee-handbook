package com.example.kode_test_app

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single {
        Glide.with(androidContext()).setDefaultRequestOptions(
            RequestOptions()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )
    }
}

