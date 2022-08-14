package com.example.feature.presentation.home.model

import com.example.core.ui.UiText
import com.example.feature.domain.model.DomainDataSource

sealed class UiSideEffect {
    data class ShowSnackbar(val message: UiText) : UiSideEffect()
    data class NavigateToDetails(val user: DomainDataSource) : UiSideEffect()
    object NavigateToErrorScreen : UiSideEffect()
    object ShowFilterDialog : UiSideEffect()
}