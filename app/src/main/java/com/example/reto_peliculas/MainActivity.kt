package com.example.reto_peliculas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.reto_peliculas.presentation.navigation.NavigationWrapper
import com.example.reto_peliculas.ui.theme.Reto_PeliculasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Reto_PeliculasTheme {
                NavigationWrapper()
            }
        }
    }
}
