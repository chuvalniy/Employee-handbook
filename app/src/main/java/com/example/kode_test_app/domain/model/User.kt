package com.example.kode_test_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_db")
data class User(
    val avatarUrl: String,
    val birthday: String,
    val department: String,
    val firstName: String,
    @PrimaryKey val id: String,
    val lastName: String,
    val position: String,
    val userTag: String
)

data class UserResponse(
    val items: List<User>
)