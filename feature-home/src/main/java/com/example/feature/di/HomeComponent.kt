package com.example.feature.di

import androidx.annotation.RestrictTo
import com.bumptech.glide.RequestManager
import com.example.core.core.ConnectivityObserver
import com.example.core_data.repository.HomeRepository
import com.example.core_preferences.UserPreferences
import com.example.feature.fragment.FilterBottomSheetFragment
import com.example.feature.fragment.HomeFragment
import dagger.Component
import javax.inject.Scope
import kotlin.properties.Delegates.notNull


@Scope
annotation class HomeScope

@Component(dependencies = [HomeDeps::class])
@HomeScope
internal interface HomeComponent {

    fun inject(fragment: HomeFragment)
    fun inject(fragment: FilterBottomSheetFragment)

    @Component.Builder
    interface Builder {

        fun deps(deps: HomeDeps): Builder

        fun build(): HomeComponent
    }
}

interface HomeDeps {
    val glide: RequestManager
    val repository: HomeRepository
    val userPreferences: UserPreferences
    val connectivityObserver: ConnectivityObserver
}

interface HomeDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: HomeDeps

    companion object : HomeDepsProvider by HomeDepsStore
}

object HomeDepsStore : HomeDepsProvider {
    override var deps: HomeDeps by notNull()
}


