package com.example.core.core

sealed class Resource<T>(val data: T? = null, val error: UiText? = null) {
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(data: T? = null, error: UiText?) : Resource<T>(data, error)
}
