package com.example.reto_peliculas.data.model.response

import com.example.reto_peliculas.data.local.entity.MovieEntity

data class MovieListResponse(
    val dates: DatesResponse,
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int,
)

data class DatesResponse(
    val maximum: String,
    val minimum: String
)

data class MovieResponse(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieResponse.toEntity(page: Int): MovieEntity = MovieEntity(
    adult = adult,
    backdrop_path = backdrop_path,
    genre_ids = genre_ids,
    id = id,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count,
    totalPage = page
)



