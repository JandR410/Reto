package com.example.reto_peliculas.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.reto_peliculas.data.local.entity.MovieEntity
import com.example.reto_peliculas.presentation.component.DetailsMovie

@Composable
fun DetailsScreen(
    movie: MovieEntity?
){
    DetailsMovie(
        movie = movie
    )
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(null)
}