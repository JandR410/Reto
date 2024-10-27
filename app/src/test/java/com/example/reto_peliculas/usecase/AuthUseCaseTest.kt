package com.example.reto_peliculas.usecase

import com.example.reto_peliculas.domain.repository.MovieRepository
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class AuthUseCaseTest {

    private val movieRepository: MovieRepository = Mockito.mock(MovieRepository::class.java)
    private val authUseCase = AuthUseCase(movieRepository)

    @Test
    fun `should return true when user and password are valid`() {
        val user = "Admin"
        val password = "Password*123"
        Mockito.`when`(movieRepository.validateUser(user, password)).thenReturn(true)
        val result = authUseCase(user, password)
        assertTrue(result)
    }

    @Test
    fun `should return false when user and password are invalid`() {
        val user = "InvalidUser"
        val password = "WrongPassword"
        Mockito.`when`(movieRepository.validateUser(user, password)).thenReturn(false)
        val result = authUseCase(user, password)
        assertFalse(result)
    }
}