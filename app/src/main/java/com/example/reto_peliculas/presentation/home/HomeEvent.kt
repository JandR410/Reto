package com.example.reto_peliculas.presentation.home

import com.example.reto_peliculas.data.local.entity.MovieEntity
import com.example.reto_peliculas.utils.base.ScreenEvent

sealed class HomeEvent: ScreenEvent {
    data class NavigateDetails(val movie: MovieEntity): HomeEvent()
}