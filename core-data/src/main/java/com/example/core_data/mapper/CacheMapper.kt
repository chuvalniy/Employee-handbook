package com.example.core_data.mapper

import com.example.core.core.Mapper
import com.example.core.utils.fromTimestampToAge
import com.example.core.utils.fromTimestampToBirthday
import com.example.core.utils.fromTimestampToBirthdayFull
import com.example.core.utils.toTimestamp
import com.example.core_database.model.CacheUser
import com.example.core_model.DomainUser
import javax.inject.Inject

class CacheMapper @Inject constructor(): Mapper<CacheUser, DomainUser> {

    override fun map(data: CacheUser): DomainUser {
        return DomainUser(
            avatarUrl = data.avatarUrl,
            birthdayDay = data.birthday.toTimestamp().fromTimestampToBirthday(),
            age = data.birthday.toTimestamp().fromTimestampToAge(),
            birthdayFull = data.birthday.toTimestamp().fromTimestampToBirthdayFull(),
            department = data.department.toUiDepartment(),
            name = "${data.firstName} ${data.lastName}",
            id = data.id,
            position = data.position,
            userTag = data.userTag.lowercase(),
            phone = data.phone
        )
    }
}

// TODO?
private fun String.toUiDepartment(): String {
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