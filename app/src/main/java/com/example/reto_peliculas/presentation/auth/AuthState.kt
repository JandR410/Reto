package com.example.reto_peliculas.presentation.auth

import com.example.reto_peliculas.utils.base.ScreenState

data class AuthState(
    val user: String,
    val password: String
): ScreenState {
    companion object{
        fun buildInitialState() = AuthState(
            user = "Admin",
            password = "Password*123"
        )
    }
}