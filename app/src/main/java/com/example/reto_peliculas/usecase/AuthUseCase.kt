package com.example.reto_peliculas.usecase

import com.example.reto_peliculas.domain.repository.MovieRepository

class AuthUseCase (
    private val movieRepository: MovieRepository
) {
    operator fun invoke(
        user: String,
        password: String
    ):  Boolean = movieRepository.validateUser(user,password)
}