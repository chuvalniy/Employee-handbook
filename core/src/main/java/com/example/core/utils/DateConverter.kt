package com.example.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun convertFromDateIntoTimestamp(date: String): Long {
    return SimpleDateFormat("yyyy-MM-dd").parse(date).time
}

fun convertFromTimestampIntoDate(timestamp: Long, convertType: ConvertType): String {
    return when (convertType) {
        ConvertType.NUMBER_OF_YEARS -> {
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            val ageOfBirth = SimpleDateFormat("yyyy").format(timestamp)
            (currentYear - ageOfBirth.toInt()).toString()
        }
        ConvertType.DAY_OF_BIRTH -> {
            SimpleDateFormat("d MMM").format(System.currentTimeMillis()).lowercase()
//            SimpleDateFormat("d MMM").format(timestamp).lowercase()
        }
        ConvertType.FULL_DATE -> {
            SimpleDateFormat("d MMMM yyyy").format(timestamp)
        }
    }
}



enum class ConvertType {
    DAY_OF_BIRTH, NUMBER_OF_YEARS, FULL_DATE
}