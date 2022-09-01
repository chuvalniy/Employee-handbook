package com.example.feature_details.model

import com.example.core.presentation.UiSideEffect


sealed class DetailSideEffect : UiSideEffect {
    object NavigateBack : DetailSideEffect()
}