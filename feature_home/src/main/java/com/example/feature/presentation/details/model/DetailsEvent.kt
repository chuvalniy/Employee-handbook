package com.example.feature.presentation.details.model

sealed class DetailsEvent {
    object BackButtonPressed: DetailsEvent()
}