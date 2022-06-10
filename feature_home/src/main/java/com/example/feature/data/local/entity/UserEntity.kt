package com.example.feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature.domain.model.User

@Entity(tableName = "user_db")
data class UserEntity(
    val avatarUrl: String,
    val timestamp: Long,
    val department: String,
    val firstName: String,
    @PrimaryKey val id: String,
    val lastName: String,
    val position: String,
    val userTag: String,
    val phone: String
) {
    fun toUser(): User {
        return User(
            avatarUrl = avatarUrl,
            timestamp = timestamp,
            department = department,
            name = "$firstName $lastName",
            id = id,
            position = position,
            userTag = userTag.lowercase(),
            phone = phone
        )
    }
}
