package com.example.feature.presentation.home.model

import com.example.feature.domain.model.DomainDataSource

sealed class UiSideEffect {
    data class ShowSnackbar(val message: String) : UiSideEffect()
    data class NavigateToDetails(val user: DomainDataSource) : UiSideEffect()
    object NavigateToErrorScreen : UiSideEffect()
    object NavigateToHomeScreen : UiSideEffect()
    object ShowFilterDialog : UiSideEffect()
}