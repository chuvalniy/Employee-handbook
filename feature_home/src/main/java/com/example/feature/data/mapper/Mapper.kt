package com.example.feature.data.mapper

import com.example.core.utils.fromTimestampToAge
import com.example.core.utils.fromTimestampToBirthday
import com.example.core.utils.fromTimestampToBirthdayFull
import com.example.core.utils.toTimestamp
import com.example.feature.data.local.cache.entity.CacheDataSource
import com.example.feature.data.remote.dto.CloudDataSource
import com.example.feature.domain.model.DomainDataSource


fun CacheDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        avatarUrl = avatarUrl,
        birthdayDay = birthday.toTimestamp().fromTimestampToBirthday(),
        age = birthday.toTimestamp().fromTimestampToAge(),
        birthdayFull = birthday.toTimestamp().fromTimestampToBirthdayFull(),
        department = department.toUiDepartment(),
        name = "$firstName $lastName",
        id = id,
        position = position,
        userTag = userTag.lowercase(),
        phone = phone
    )
}

fun String.toUiDepartment(): String {
    return when (this) {
        "android" -> "Android"
        "ios" -> "iOS"
        "design" -> "Design"
        "management" -> "Management"
        "qa" -> "QA"
        "hr" -> "HR"
        "pr" -> "PR"
        "backend" -> "Backend"
        "frontend" -> "frontend"
        "back_office" -> "Back ofice"
        "support" -> "Support"
        "analytics" -> "Analytics"
        else -> "All"
    }
}

fun CloudDataSource.toCacheDataSource(): CacheDataSource {
    return CacheDataSource(
        avatarUrl = avatarUrl,
        birthday = birthday,
        department = department,
        firstName = firstName,
        id = id,
        lastName = lastName,
        position = position,
        userTag = userTag,
        phone = phone
    )
}

