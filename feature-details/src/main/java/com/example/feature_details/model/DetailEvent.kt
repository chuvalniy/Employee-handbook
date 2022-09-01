package com.example.feature_details.model

import com.example.core.presentation.UiEvent

sealed class DetailEvent : UiEvent {
    object BackButtonPressed : DetailEvent()
}