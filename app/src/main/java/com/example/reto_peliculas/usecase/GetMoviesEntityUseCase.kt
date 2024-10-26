package com.example.reto_peliculas.usecase

import com.example.reto_peliculas.data.local.entity.MovieEntity
import com.example.reto_peliculas.domain.repository.MovieRepository
import com.example.reto_peliculas.utils.Result
import javax.inject.Inject

class GetMoviesEntityUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(page: Int): Result<List<MovieEntity>> {
        return try {
            val movies = movieRepository.getMoviesByPage(page)
            Result.Success(movies)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}