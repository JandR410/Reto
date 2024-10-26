package com.example.reto_peliculas.domain.repository

import com.example.reto_peliculas.data.local.entity.MovieEntity

interface MovieRepository {

    fun validateUser(
        user: String,
        password: String
    ): Boolean

    suspend fun getMoviesByPage(page: Int): List<MovieEntity>

    suspend fun fetchMovies(page: Int): List<MovieEntity>
}