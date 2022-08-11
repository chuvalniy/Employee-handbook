package com.example.feature.presentation.details.view_model

import com.example.feature.domain.model.DomainDataSource

data class DetailsState(
    val data: DomainDataSource = DomainDataSource(
        avatarUrl = "",
        timestamp = 0L,
        department = "",
        name = "",
        id = "",
        position = "",
        userTag = "",
        phone = ""
    )
)