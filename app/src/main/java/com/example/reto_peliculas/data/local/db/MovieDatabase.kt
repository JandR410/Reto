package com.example.reto_peliculas.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.reto_peliculas.data.local.dao.MovieDao
import com.example.reto_peliculas.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}