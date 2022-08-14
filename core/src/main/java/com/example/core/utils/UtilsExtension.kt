package com.example.core.utils

import android.annotation.SuppressLint
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
