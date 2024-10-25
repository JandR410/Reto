package com.example.reto_peliculas.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reto_peliculas.presentation.auth.AuthScreen
import com.example.reto_peliculas.presentation.auth.AuthViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            val viewModel: AuthViewModel = hiltViewModel()
            val screenState = viewModel.screenState.collectAsState().value
            AuthScreen(
                state = screenState.state,
                screenHandler = {

                },
                navigate = {
                    navController.navigate("home/$it")
                }
            )
        }
    }
}