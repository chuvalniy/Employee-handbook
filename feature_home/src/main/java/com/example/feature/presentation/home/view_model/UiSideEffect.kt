package com.example.feature.presentation.home.view_model

import com.example.feature.domain.model.DomainDataSource

sealed class UiSideEffect {
    data class ShowSnackbar(val message: String) : UiSideEffect()
    data class NavigateToDetails(val user: DomainDataSource) : UiSideEffect()
    object ShowFilterDialog : UiSideEffect()
}