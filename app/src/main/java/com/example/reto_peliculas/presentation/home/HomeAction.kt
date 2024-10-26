package com.example.reto_peliculas.presentation.home

import com.example.reto_peliculas.data.local.entity.MovieEntity
import com.example.reto_peliculas.utils.base.ScreenAction

sealed class HomeAction: ScreenAction {
    data class Pagination(val page: String): HomeAction()
    data class ItemSelect(val movieSelect: MovieEntity): HomeAction()
}
