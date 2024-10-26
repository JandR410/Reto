package com.example.reto_peliculas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.reto_peliculas.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Transaction
    suspend fun insertMoviesWithPage(movies: List<MovieEntity>, page: Int) {
        movies.forEach { it.page = page }
        insertAll(movies)
    }

    @Query("SELECT * FROM movies WHERE page = :page")
    suspend fun getMoviesByPage(page: Int): List<MovieEntity>

    @Query("DELETE FROM movies")
    suspend fun clearAllMovies()
}