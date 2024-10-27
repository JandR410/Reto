package com.example.reto_peliculas.data.repository

import com.example.reto_peliculas.data.local.dao.MovieDao
import com.example.reto_peliculas.data.local.entity.MovieEntity
import com.example.reto_peliculas.data.model.response.DatesResponse
import com.example.reto_peliculas.data.model.response.MovieListResponse
import com.example.reto_peliculas.data.model.response.MovieResponse
import com.example.reto_peliculas.data.model.response.toEntity
import com.example.reto_peliculas.data.remote.MovieApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class MovieRepositoryImplTest {

    private lateinit var movieApi: MovieApi
    private lateinit var movieDao: MovieDao
    private lateinit var movieRepository: MovieRepositoryImpl

    @Before
    fun setUp() {
        movieApi = mock(MovieApi::class.java)
        movieDao = mock(MovieDao::class.java)
        movieRepository = MovieRepositoryImpl(movieApi, movieDao)
    }

    @Test
    fun `validateUser returns true for correct credentials`() {
        val user = "Admin"
        val password = "Password*123"

        val result = movieRepository.validateUser(user, password)

        assertTrue(result)
    }

    @Test
    fun `validateUser returns false for incorrect credentials`() {
        val user = "Admin"
        val password = "wrongPassword"

        val result = movieRepository.validateUser(user, password)

        assertTrue(!result)
    }

    @Test
    fun `getMoviesByPage calls movieDao and returns movies`(): Unit = runBlocking {
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
        `when`(movieDao.getMoviesByPage(page)).thenReturn(expectedMovies)

        val result = movieRepository.getMoviesByPage(page)

        assertEquals(expectedMovies, result)
        verify(movieDao).getMoviesByPage(page) // Verifica que se haya llamado al método
    }

    @Test
    fun `fetchMovies fetches movies from API and stores them in Dao`() = runBlocking {
        val mockDatesResponse = DatesResponse(
            maximum = "2024-12-31",
            minimum = "2024-01-01"
        )
        val mockMovieResponse = MovieResponse(
            adult = false,
            backdrop_path = "/backdropPath.jpg",
            genre_ids = listOf(28, 12, 16),
            id = 12345,
            original_language = "en",
            original_title = "Original Movie Title",
            overview = "This is an example overview of the movie.",
            popularity = 1234.56,
            poster_path = "/posterPath.jpg",
            release_date = "2024-10-25",
            title = "Movie Title",
            video = false,
            vote_average = 8.5,
            vote_count = 1500
        )
        val mockMovieListResponse = MovieListResponse(
            dates = mockDatesResponse,
            page = 1,
            results = listOf(mockMovieResponse),
            total_pages = 10,
            total_results = 100
        )
        Mockito.`when`(movieApi.getUpcomingMovies(1)).thenReturn(mockMovieListResponse)

        val movieEntities = mockMovieListResponse.results.map { it.toEntity(1) }

        val result = movieRepository.fetchMovies(1)

        Mockito.verify(movieApi).getUpcomingMovies(1)
        Mockito.verify(movieDao).insertMoviesWithPage(movieEntities, 0)
        assertEquals(movieEntities, result)
    }



    @Test(expected = Exception::class)
    fun `fetchMovies throws exception when API call fails`(): Unit = runBlocking {
        val page = 1
        `when`(movieApi.getUpcomingMovies(page)).thenThrow(Exception("Error fetching movies"))

        movieRepository.fetchMovies(page)
    }
}