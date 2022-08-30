package com.example.core.utils

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    fun hasInternetConnection(): Boolean

    enum class Status {
        Available, Unavailable
    }
}