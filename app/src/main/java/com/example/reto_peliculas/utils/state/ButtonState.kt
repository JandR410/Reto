package com.example.reto_peliculas.utils.state

data class ButtonState(
    val text: String,
    val onButtonClicked: () -> Unit
)