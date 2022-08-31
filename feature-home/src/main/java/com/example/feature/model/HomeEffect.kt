package com.example.feature.model

import com.example.core.core.UiText
import com.example.core.presentation.UiSideEffect
import com.example.core_model.DomainUser

sealed class HomeEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText) : HomeEffect()
    data class NavigateToDetails(val user: DomainUser) : HomeEffect()
    object NavigateToErrorScreen : HomeEffect()
    object ShowFilterDialog : HomeEffect()
}