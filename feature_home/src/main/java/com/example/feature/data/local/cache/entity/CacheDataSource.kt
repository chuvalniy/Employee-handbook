package com.example.feature.data.local.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.utils.fromTimestampToAge
import com.example.core.utils.fromTimestampToBirthday
import com.example.core.utils.fromTimestampToBirthdayFull
import com.example.core.utils.toTimestamp
import com.example.feature.domain.model.DomainDataSource

@Entity(tableName = "user_db")
data class CacheDataSource(
    @PrimaryKey val id: String,
    val avatarUrl: String,
    val birthday: String,
    val department: String,
    val firstName: String,
    val lastName: String,
    val position: String,
    val userTag: String,
    val phone: String
) {
    fun toDomainDataSource(): DomainDataSource {
        return DomainDataSource(
            avatarUrl = avatarUrl,
            birthdayDay = birthday.toTimestamp().fromTimestampToBirthday(),
            age = birthday.toTimestamp().fromTimestampToAge(),
            birthdayFull = birthday.toTimestamp().fromTimestampToBirthdayFull(),
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
