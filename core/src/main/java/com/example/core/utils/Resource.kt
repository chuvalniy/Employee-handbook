package com.example.core.utils

sealed class Resource<T>(val data: T? = null, val error: Exception? = null) {
    class Loading<T>(val isLoading: Boolean = true, ) : Resource<T>(null)
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(data: T? = null, error: Exception?) : Resource<T>(data, error)
}
