package com.example.feature_details.model

import com.example.core.presentation.UiState
import com.example.core_model.model.DomainUser

data class DetailState(
    val data: DomainUser? = null
) : UiState