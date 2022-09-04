package com.example.kode_test_app.di

import android.content.Context
import com.bumptech.glide.RequestManager
import com.example.core.core.ConnectivityObserver
import com.example.core_data.repository.DetailRepository
import com.example.core_data.repository.HomeRepository
import com.example.core_preferences.UserPreferences
import com.example.feature.di.HomeDeps
import com.example.feature_details.di.DetailsDeps
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Scope
annotation class AppScope


@Component(modules = [AppModule::class])
@AppScope
interface AppComponent : HomeDeps, DetailsDeps {

    override val glide: RequestManager
    override val detailRepository: DetailRepository
    override val homeRepository: HomeRepository
    override val connectivityObserver: ConnectivityObserver
    override val userPreferences: UserPreferences

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}
