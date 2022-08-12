package com.example.feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature.domain.model.DomainDataSource

@Entity(tableName = "user_db")
data class CacheDataSource(
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
    fun toDomainDataSource(): DomainDataSource {
        return DomainDataSource(
            avatarUrl = avatarUrl,
            timestamp = timestamp,
            department = department.toDomainDepartment(),
            name = "$firstName $lastName",
            id = id,
            position = position,
            userTag = userTag.lowercase(),
            phone = phone
        )
    }
}

fun String.toDomainDepartment(): String {
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
