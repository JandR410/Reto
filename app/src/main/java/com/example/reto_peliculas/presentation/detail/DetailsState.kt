package com.example.reto_peliculas.presentation.detail

import com.example.reto_peliculas.data.local.entity.MovieEntity
import com.example.reto_peliculas.utils.base.ScreenState

data class DetailsState(
    val movie: MovieEntity?
) : ScreenState {
    companion object {
        fun buildInitialState() = DetailsState(
            movie = null
        )
    }
}
