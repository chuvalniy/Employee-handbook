package com.example.kode_test_app.core.utils

import java.text.SimpleDateFormat

fun convertFromDateIntoTimestamp(date: String): Long {
    return SimpleDateFormat("yyyy-MM-dd").parse(date).time
}

fun convertFromTimestampIntoDate(timestamp: Long, convertType: ConvertType): String {
    return when (convertType) {
        ConvertType.NUMBER_OF_YEARS -> {
            val currentTimestamp = System.currentTimeMillis() / 1000L
            SimpleDateFormat("yy").format(currentTimestamp - timestamp)
        }
        ConvertType.DAY_OF_BIRTH -> {
            SimpleDateFormat("yyyy").format(timestamp)
        }
        ConvertType.FULL_DATE -> {
            SimpleDateFormat("d MMMM yyyy").format(timestamp)
        }
    }
}

enum class ConvertType {
    DAY_OF_BIRTH, NUMBER_OF_YEARS, FULL_DATE
}