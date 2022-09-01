package com.example.feature_details.model

import com.example.core.presentation.UiSideEffect


sealed class DetailsSideEffect : UiSideEffect {
    object NavigateBack : DetailsSideEffect()
}