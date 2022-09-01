package com.example.core.presentation

interface EventHandler<E: UiEvent> {

    fun onEvent(event: E)
}