package com.example.reto_peliculas.presentation.detail

import com.example.reto_peliculas.utils.state.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(

): BaseViewModel<DetailsState,DetailsAction,DetailsEvent>(initialState = DetailsState.buildInitialState()) {

    override fun handleScreenActions(action: DetailsAction) {
        TODO("Not yet implemented")
    }
}