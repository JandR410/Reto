package com.example.reto_peliculas.utils.state

data class DialogState(
    val title: String,
    val message: String,
    val firstButton: ButtonState?,
    val secondButton: ButtonState?,
)