package com.example.reto_peliculas.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.reto_peliculas.data.local.entity.MovieEntity

@Composable
fun AppMovie(
    onclick: (MovieEntity) -> Unit,
    movie: MovieEntity
) {
    Column(
        modifier = Modifier
            .padding(14.dp)
            .clickable { onclick(movie) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Companion.Black, RoundedCornerShape(14.dp))
        ) {
            Row(
                modifier = Modifier.padding(14.dp)
            ) {
                Column {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                        contentDescription = "Imagen desde internet",
                        modifier = Modifier.size(100.dp)
                    )
                }
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = movie.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Launch: ${movie.release_date}"
                    )
                    Text(
                        text = "Language: ${movie.original_language}"
                    )
                    Text(
                        text = "Popularity: ${movie.popularity}"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppMoviePreview() {
    val movie = MovieEntity(
        adult = false,
        backdrop_path = "/417tYZ4XUyJrtyZXj7HpvWf1E8f.jpg",
        genre_ids = listOf(16, 878, 10751),
        id = 1184918,
        original_language = "en",
        original_title = "The Wild Robot",
        overview = "After a shipwreck, an intelligent robot called Roz is stranded on an uninhabited island. To survive the harsh environment, Roz bonds with the island's animals and cares for an orphaned baby goose.",
        popularity = 5400.805,
        poster_path = "/wTnV3PCVW5O92JMrFvvrRcV39RU.jpg",
        release_date = "2024-09-12",
        title = "The Wild Robot",
        video = false,
        vote_average = 8.638,
        vote_count = 1547
    )
    AppMovie(
        onclick = {},
        movie = movie
    )
}