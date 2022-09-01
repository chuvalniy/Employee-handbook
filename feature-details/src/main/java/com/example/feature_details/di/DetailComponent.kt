package com.example.feature_details.di

import androidx.annotation.RestrictTo
import com.bumptech.glide.RequestManager
import com.example.core_data.repository.DetailsRepository
import com.example.feature_details.fragment.DetailFragment
import dagger.Component
import javax.inject.Scope
import kotlin.properties.Delegates
import kotlin.properties.Delegates.notNull

@Scope
annotation class DetailsScope

@Component(dependencies = [DetailsDeps::class])
@DetailsScope
internal interface DetailsComponent {

    fun inject(fragment: DetailFragment)

    @Component.Builder
    interface Builder {

        fun deps(deps: DetailsDeps): Builder

        fun build(): DetailsComponent
    }
}

interface DetailsDeps {
    val glide: RequestManager
    val detailsRepository: DetailsRepository
}

interface DetailsDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: DetailsDeps

    companion object : DetailsDepsProvider by DetailsDepsStore
}

object DetailsDepsStore : DetailsDepsProvider {
    override var deps: DetailsDeps by notNull()
}
