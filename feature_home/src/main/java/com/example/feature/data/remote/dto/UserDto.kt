package com.example.feature.data.remote.dto

import com.example.core.utils.convertFromDateIntoTimestamp
import com.example.feature.data.local.entity.CacheDataSource


data class UserResponse(
    val items: List<UserDto>
)

data class UserDto(
    val avatarUrl: String,
    val birthday: String,
    val department: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val position: String,
    val userTag: String,
    val phone: String
) {
    fun toUserEntity(): CacheDataSource {
        return CacheDataSource(
            avatarUrl = avatarUrl,
            timestamp = convertFromDateIntoTimestamp(birthday),
            department = department,
            firstName = firstName,
            id = id,
            lastName = lastName,
            position = position,
            userTag = userTag,
            phone = phone
        )
    }
}
