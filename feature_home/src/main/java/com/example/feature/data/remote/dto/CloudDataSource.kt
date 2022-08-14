package com.example.feature.data.remote.dto


data class CloudDataSourceResponse(
    val items: List<CloudDataSource>
)

data class CloudDataSource(
    val avatarUrl: String,
    val birthday: String,
    val department: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val position: String,
    val userTag: String,
    val phone: String
)