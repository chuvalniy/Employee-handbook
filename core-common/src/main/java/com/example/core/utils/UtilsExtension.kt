package com.example.core.utils

import android.annotation.SuppressLint
import com.example.core.R
import com.example.core.core.Resource
import com.example.core.core.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.toTimestamp(): Long {
    return SimpleDateFormat("yyyy-MM-dd").parse(this).time
}

@SuppressLint("SimpleDateFormat")
fun Long.fromTimestampToBirthday(): String {
    return SimpleDateFormat("d MMM").format(this).lowercase()
}

@SuppressLint("SimpleDateFormat")
fun Long.fromTimestampToBirthdayFull(): String {
    return SimpleDateFormat("d MMMM yyyy").format(this)
}

@SuppressLint("SimpleDateFormat")
fun Long.fromTimestampToAge(): String {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val ageOfBirth = SimpleDateFormat("yyyy").format(this)
    return (currentYear - ageOfBirth.toInt()).toString()
}

inline fun <T> Flow<Resource<T>>.onEachResource(
    crossinline onError: (UiText) -> Unit,
    crossinline onSuccess: (T) -> Unit,
    crossinline onLoading: (Boolean) -> Unit = { },
) = onEach { result ->
    when (result) {
        is Resource.Error -> onError(
            result.error
                ?: UiText.StringResource(R.string.unexpected_error)
        )
        is Resource.Loading -> {
            onLoading(result.isLoading)
        }
        is Resource.Success -> result.data?.let(onSuccess)
    }
}
