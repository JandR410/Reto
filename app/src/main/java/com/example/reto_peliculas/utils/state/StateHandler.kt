package com.example.reto_peliculas.utils.state

import com.example.reto_peliculas.utils.base.BaseScreenState
import com.example.reto_peliculas.utils.base.ScreenState
import kotlinx.coroutines.flow.StateFlow

interface StateHandler<S : ScreenState> {
    val screenState: StateFlow<BaseScreenState<S>>
    val state: S

    fun setState(newState: S)

    fun showLoading()
    fun hideLoading()

    fun showDialog(dialog: DialogState)
    fun showGenericDialog(dialog: DialogState, onButtonClicked: () -> Unit)
    fun dismissDialog()

    fun showNoInternetScreen(onButtonRetryClicked: () -> Unit)
}