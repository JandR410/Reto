package com.example.reto_peliculas.utils.state

import com.example.reto_peliculas.utils.base.BaseScreenState
import com.example.reto_peliculas.utils.base.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StateHandlerImpl<S : ScreenState>(initialState: S) : StateHandler<S> {
    private val _screenState = MutableStateFlow(BaseScreenState(initialState))
    override val screenState: StateFlow<BaseScreenState<S>> = _screenState

    override val state: S
        get() = screenState.value.state

    override fun setState(newState: S) {
        _screenState.value = _screenState.value.copy(state = newState)
    }

    override fun showLoading() {
        _screenState.value = screenState.value.copy(loading = true)
    }

    override fun hideLoading() {
        _screenState.value = screenState.value.copy(loading = false)
    }

    override fun showDialog(dialog: DialogState) {
        _screenState.value = screenState.value.copy(dialog = dialog)
    }

    override fun showNoInternetScreen(onButtonRetryClicked: () -> Unit) {
        _screenState.value =
            screenState.value.copy(noInternetDialog = NoInternetDialogState(onButtonRetryClicked))
    }

    override fun showGenericDialog(dialog: DialogState, onButtonClicked: () -> Unit) {
        _screenState.value = screenState.value.copy(dialog = dialog)
    }

    override fun dismissDialog() {
        _screenState.value = screenState.value.copy(dialog = null)
    }
}