package com.example.feature_details.model

import com.example.core.presentation.UiEvent

sealed class DetailsEvent : UiEvent {
    object BackButtonPressed : DetailsEvent()
}