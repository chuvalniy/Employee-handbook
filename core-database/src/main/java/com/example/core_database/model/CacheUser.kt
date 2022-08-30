package com.example.core_database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core_database.database.CacheDatabase

@Entity(tableName = CacheDatabase.DATABASE_NAME)
data class CacheUser(
    @PrimaryKey val id: String,
    val avatarUrl: String,
    val birthday: String,
    val department: String,
    val firstName: String,
    val lastName: String,
    val position: String,
    val userTag: String,
    val phone: String
)
