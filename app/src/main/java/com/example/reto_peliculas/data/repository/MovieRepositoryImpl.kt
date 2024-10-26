package com.example.reto_peliculas.data.repository

import com.example.reto_peliculas.data.local.dao.MovieDao
import com.example.reto_peliculas.data.local.entity.MovieEntity
import com.example.reto_peliculas.data.model.response.toEntity
import com.example.reto_peliculas.data.remote.MovieApi
import com.example.reto_peliculas.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun validateUser(user: String, password: String): Boolean {
        return user == "Admin" && password == "Password*123"
    }

    override suspend fun getMoviesByPage(page: Int): List<MovieEntity> {
        return movieDao.getMoviesByPage(page)
    }

    override suspend fun fetchMovies(page: Int): List<MovieEntity> {
        return try {
            val response = movieApi.getUpcomingMovies(page)
            val movieEntities = response.results.map { it.toEntity(page) }
            movieDao.insertMoviesWithPage(movieEntities, page - 1)
            movieEntities
        } catch (e: Exception) {
            throw e
        }
    }
}