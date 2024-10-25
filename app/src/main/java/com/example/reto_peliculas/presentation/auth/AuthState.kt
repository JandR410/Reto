package com.example.reto_peliculas.presentation.auth

import com.example.reto_peliculas.utils.base.ScreenState

data class AuthState(
    val user: String,
    val password: String,
    val enabled: Boolean,
): ScreenState {
    fun isEnabledBottom() = user.isNotEmpty() && password.isNotEmpty()
    companion object{
        fun buildInitialState() = AuthState(
            user = "",
            password = "",
            enabled = false
        )
    }
}