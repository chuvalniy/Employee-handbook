package com.example.feature.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainDataSource(
    val avatarUrl: String,
    val birthdayDay: String,
    val age: String,
    val birthdayFull: String,
    val department: String,
    val name: String,
    val id: String,
    val position: String,
    val userTag: String,
    val phone: String
): Parcelable
