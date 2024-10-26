package com.example.reto_peliculas.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.reto_peliculas.data.local.entity.MovieEntity

@Composable
fun DetailsMovie(
    movie: MovieEntity?
) {
    val scrollState = rememberScrollState()

    movie?.let {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                contentDescription = "Imagen desde internet",
                modifier = Modifier
                    .width(120.dp)
                    .height(200.dp)
            )

            Text(
                text = movie.title.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier.padding(top = 14.dp),
                text = "${movie.vote_average}"
            )
            Text(
                modifier = Modifier.padding(top = 14.dp),
                text = movie.release_date
            )
            Text(
                modifier = Modifier.padding(top = 14.dp),
                text = movie.overview
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DetailsMoviePreview() {
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
    DetailsMovie(
        movie
    )
}