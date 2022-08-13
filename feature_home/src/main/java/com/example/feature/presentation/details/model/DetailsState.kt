package com.example.feature.presentation.details.model

import com.example.feature.domain.model.DomainDataSource

data class DetailsState(
    val data: DomainDataSource = DomainDataSource(
        avatarUrl = "",
        age = "",
        birthdayDay = "",
        birthdayFull = "",
        department = "",
        name = "",
        id = "",
        position = "",
        userTag = "",
        phone = ""
    )
)