package com.example.feature.presentation.home.view_model

sealed class UiSideEffect {
    data class ShowSnackbar(val message: String) : UiSideEffect()
    data class NavigateToDetails(val id: String) : UiSideEffect()
    object ShowFilterDialog : UiSideEffect()
}