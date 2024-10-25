package com.example.reto_peliculas.presentation.auth

import com.example.reto_peliculas.utils.state.BaseViewModel
import com.example.reto_peliculas.utils.state.ButtonState
import com.example.reto_peliculas.utils.state.DialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(

) : BaseViewModel<AuthState, AuthAction, AuthEvent>(initialState = AuthState.buildInitialState()) {

    override fun handleScreenActions(action: AuthAction) {
        when (action) {
            is AuthAction.HandleUserChange -> onChangedUser(action.user)
            is AuthAction.HandlePasswordChange -> onChangedPassword(action.password)
            AuthAction.ValidateCredential -> validateCredential()
        }
    }

    private fun onChangedUser(user: String) {
        setState(state.copy(user = user))
        enabledBottom()
    }

    private fun onChangedPassword(password: String) {
        setState(state.copy(password = password))
        enabledBottom()
    }

    private fun enabledBottom() {
        setState(state.copy(enabled = state.isEnabledBottom()))
    }

    private fun validateCredential() {
        if (state.user == "Admin" && state.password == "Password*123")
            sendEvent(AuthEvent.NavigateToHome)
        else
            dialogError()
    }

    private fun dialogError() {
        showDialog(
            DialogState(
                title = "Error",
                message = "Las credenciales no coinciden",
                firstButton = ButtonState(
                    text = "Volver a intentar",
                    onButtonClicked = {
                        dismissDialog()
                    }
                ),
                secondButton = null
            )
        )
    }
}