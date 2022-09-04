package com.example.feature.model

import com.example.core.core.UiText
import com.example.core.presentation.UiSideEffect

sealed class HomeEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText) : HomeEffect()
    data class NavigateToDetails(val id: String) : HomeEffect()
    object NavigateToErrorScreen : HomeEffect()
    object ShowFilterDialog : HomeEffect()
}