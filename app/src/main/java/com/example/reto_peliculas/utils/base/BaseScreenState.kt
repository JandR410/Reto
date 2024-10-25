package com.example.reto_peliculas.utils.base

import com.example.reto_peliculas.utils.state.DialogState
import com.example.reto_peliculas.utils.state.NoInternetDialogState

data class BaseScreenState<T>(
    val state: T,
    val loading: Boolean = false,
    val dialog: DialogState? = null,
    val noInternetDialog: NoInternetDialogState? = null
)