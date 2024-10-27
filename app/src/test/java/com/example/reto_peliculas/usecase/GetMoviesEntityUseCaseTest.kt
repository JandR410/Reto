package com.example.reto_peliculas.usecase

import com.example.reto_peliculas.data.local.entity.MovieEntity
import com.example.reto_peliculas.domain.repository.MovieRepository
import com.example.reto_peliculas.utils.Result
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetMoviesEntityUseCaseTest {

    private lateinit var movieRepository: MovieRepository
    private lateinit var getMoviesEntityUseCase: GetMoviesEntityUseCase

    @Before
    fun setUp() {
        movieRepository = mock(MovieRepository::class.java)
        getMoviesEntityUseCase = GetMoviesEntityUseCase(movieRepository)
    }

    @Test
    fun `invoke returns Success with movie list when valid page is provided`() = runBlocking {
        val page = 1
        val expectedMovies = listOf(
            MovieEntity(
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
        )

        `when`(movieRepository.getMoviesByPage(page)).thenReturn(expectedMovies)

        val result = getMoviesEntityUseCase(page)

        assertTrue(result is Result.Success)
        assertEquals(expectedMovies, (result as Result.Success).data)
    }

    @Test
    fun `invoke returns Failure when an exception is thrown`() = runBlocking {

        val page = 2
        val exception = RuntimeException("Error fetching movies")
        `when`(movieRepository.getMoviesByPage(page)).thenThrow(exception)
        val result = getMoviesEntityUseCase(page)
        assertTrue(result is Result.Failure)
        assertEquals(exception, (result as Result.Failure).exception)
    }
}