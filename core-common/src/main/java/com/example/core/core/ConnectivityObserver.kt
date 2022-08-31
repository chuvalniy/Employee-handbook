package com.example.core.core

import kotlinx.coroutines.flow.Flow


// TODO
interface ConnectivityObserver {

    fun observe(): Flow<Status>

    fun hasInternetConnection(): Boolean

    enum class Status {
        Available, Unavailable
    }
}