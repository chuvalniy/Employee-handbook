package com.example.kode_test_app.domain.model

data class User(
    val avatarUrl: String,
    val timestamp: Long,
    val department: String,
    val name: String,
    val id: String,
    val position: String,
    val userTag: String,
    val phone: String
)
