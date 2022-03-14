package com.example.kode_test_app.data.remote.dto

import com.example.kode_test_app.core.utils.convertFromDateIntoTimestamp
import com.example.kode_test_app.data.local.entity.UserEntity


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
    fun toUserEntity(): UserEntity {
        return UserEntity(
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
