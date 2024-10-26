package com.example.reto_peliculas.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reto_peliculas.data.local.entity.MovieEntity
import com.example.reto_peliculas.presentation.auth.AuthScreen
import com.example.reto_peliculas.presentation.auth.AuthViewModel
import com.example.reto_peliculas.presentation.component.AppDrawer
import com.example.reto_peliculas.presentation.component.BaseScreen
import com.example.reto_peliculas.presentation.component.Scaffold
import com.example.reto_peliculas.presentation.detail.DetailsScreen
import com.example.reto_peliculas.presentation.home.HomeScreen
import com.example.reto_peliculas.presentation.home.HomeViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/home") {

        composable("/login") {
            val viewModel: AuthViewModel = hiltViewModel()
            val screenState = viewModel.screenState.collectAsState().value
            BaseScreen(baseScreenState = screenState) {
                AuthScreen(
                    state = screenState.state,
                    screenHandler = viewModel::handleScreenActions,
                    navigate = {
                        navController.navigate("/home")
                    },
                    events = viewModel.eventsFlow
                )
            }
        }

        composable("/home") {
            val viewModel: HomeViewModel = hiltViewModel()
            val screenState = viewModel.screenState.collectAsState().value
            val navigation = remember { mutableStateOf("home") }
            var currentScreen by remember { mutableIntStateOf(0) }
            val movieEntity: MovieEntity? = null
            var movie by remember { mutableStateOf(movieEntity) }
            Scaffold(
                selection = currentScreen,
                onSelectScreen = { screenName ->
                    when (screenName) {
                        "home" -> {
                            currentScreen = 0
                            navigation.value = "home"
                        }

                        "details" -> {
                            currentScreen = 1
                            navigation.value = "details"
                        }
                    }
                },
                drawerContent = {
                    AppDrawer(
                        modifier = Modifier,
                        onNavigation = { navigation.value = it },
                        onMenuItemClick = {
                            navController.navigate(it) }
                    )
                }
            ) {
                when (navigation.value) {
                    "home" -> {
                        BaseScreen(baseScreenState = screenState) {
                            HomeScreen(
                                state = screenState.state,
                                screenHandler = viewModel::handleScreenActions,
                                navigate = {
                                    movie = it
                                    currentScreen = 1
                                    navigation.value = "details"
                                },
                                events = viewModel.eventsFlow
                            )
                        }

                    }

                    "details" -> {
                        BaseScreen(baseScreenState = screenState) {
                            DetailsScreen(
                                movie = movie
                            )
                        }
                    }

                }
            }

        }

    }
}