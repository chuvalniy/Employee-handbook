package com.example.feature_details.model

sealed class DetailsEvent {
    object BackButtonPressed: DetailsEvent()
}