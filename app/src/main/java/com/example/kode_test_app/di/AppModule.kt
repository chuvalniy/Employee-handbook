package com.example.kode_test_app.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.core.core.ConnectivityObserver
import com.example.core.core.NetworkConnectivityObserver
import com.example.core_data.repository.DetailsRepository
import com.example.core_data.repository.DetailsRepositoryImpl
import com.example.core_data.repository.HomeRepository
import com.example.core_data.repository.HomeRepositoryImpl
import com.example.core_database.database.CacheDataSource
import com.example.core_database.database.CacheDatabase
import com.example.core_network.network.NetworkDataSource
import com.example.core_network.network.NetworkRetrofit
import com.example.core_preferences.UserPreferences
import com.example.core_preferences.UserUserPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module(includes = [AppBindModule::class])
object AppModule {

    @Provides
    @AppScope
    fun provideRequestManager(context: Context): RequestManager {
        return Glide.with(context).setDefaultRequestOptions(
            RequestOptions()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )
    }

    @Provides
    @AppScope
    fun provideNetworkRetrofit(): NetworkRetrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkRetrofit.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @AppScope
    fun provideCacheDatabase(context: Context): CacheDatabase {
        return Room.databaseBuilder(
            context,
            CacheDatabase::class.java,
            CacheDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @AppScope
    fun provideCacheDao(database: CacheDatabase) = database.dao

}

@Module
interface AppBindModule {


    // TODO
    @Binds
    @AppScope
    fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    @AppScope
    fun bindConnectivityObserver(networkConnectivityObserver: NetworkConnectivityObserver): ConnectivityObserver

    @Binds
    @AppScope
    fun bindUserPreferences(UserUserPreferencesImpl: UserUserPreferencesImpl): UserPreferences

    @Binds
    @AppScope
    fun bindCacheDataSource(roomCacheDataSource: CacheDataSource.RoomCacheDataSource): CacheDataSource

    @Binds
    @AppScope
    fun bindNetworkDataSource(retrofitNetworkDataSource: NetworkDataSource.RetrofitNetworkDataSource): NetworkDataSource

    @Binds
    @AppScope
    fun bindDetailsRepository(detailsRepositoryImpl: DetailsRepositoryImpl): DetailsRepository
}