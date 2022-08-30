package com.example.feature.presentation.home.model

import com.example.core.ui.UiSideEffect
import com.example.core.ui.UiText
import com.example.feature.domain.model.DomainDataSource

sealed class HomeEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText) : HomeEffect()
    data class NavigateToDetails(val user: DomainDataSource) : HomeEffect()
    object NavigateToErrorScreen : HomeEffect()
    object ShowFilterDialog : HomeEffect()
}