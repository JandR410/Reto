package com.example.reto_peliculas.presentation.auth

import com.example.reto_peliculas.utils.base.ScreenAction

sealed class AuthAction: ScreenAction {
    data class HandleUserChange(val user: String): AuthAction()
    data class HandlePasswordChange(val password: String): AuthAction()
    data object ValidateCredential: AuthAction()
}