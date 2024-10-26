package com.example.reto_peliculas.data.remote

import com.example.reto_peliculas.data.model.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = "c03c35beaffa2f5e9d1f9227f55cdedf"
    ): MovieListResponse
}