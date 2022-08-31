package com.example.core_data.mapper

import com.example.core.core.Mapper
import com.example.core_database.model.CacheUser
import com.example.core_network.model.NetworkUser
import javax.inject.Inject

class NetworkMapper @Inject constructor(): Mapper<NetworkUser, CacheUser> {

    override fun map(data: NetworkUser): CacheUser {
        return CacheUser(
            id = data.id,
            avatarUrl = data.avatarUrl,
            birthday = data.birthday,
            department = data.department,
            firstName = data.firstName,
            lastName = data.lastName,
            position = data.position,
            userTag = data.userTag,
            phone = data.phone,
        )
    }
}