package com.example.reto_peliculas.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromGenreIds(value: List<Int>?): String? {
        return value?.joinToString(",") // Convierte la lista a una cadena separada por comas
    }

    @TypeConverter
    fun toGenreIds(value: String?): List<Int>? {
        return value?.split(",")?.map { it.toInt() } // Convierte la cadena de vuelta a una lista
    }
}