package com.example.kode_test_app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kode_test_app.domain.model.User

@Entity(tableName = "user_db")
data class UserEntity(
    val avatarUrl: String,
    val birthday: String,
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
            birthday = birthday,
            department = department,
            name = "$firstName $lastName",
            id = id,
            position = position,
            userTag = userTag.lowercase(),
            phone = phone
        )
    }
}
