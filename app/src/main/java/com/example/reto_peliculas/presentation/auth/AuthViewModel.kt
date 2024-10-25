package com.example.reto_peliculas.presentation.auth

import com.example.reto_peliculas.utils.state.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(

) : BaseViewModel<AuthState, AuthAction, AuthEvent>(initialState = AuthState.buildInitialState()) {

    override fun handleScreenActions(action: AuthAction) {
        TODO("Not yet implemented")
    }
}