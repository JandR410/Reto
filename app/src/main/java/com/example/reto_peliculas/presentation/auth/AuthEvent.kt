package com.example.reto_peliculas.presentation.auth

import com.example.reto_peliculas.utils.base.ScreenEvent

sealed class AuthEvent: ScreenEvent {
    data object NavigateToHome: AuthEvent()
}