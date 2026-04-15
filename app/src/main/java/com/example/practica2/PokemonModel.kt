package com.example.practica2

import kotlinx.serialization.Serializable
// Modelo de datos. Solo extraemos el nombre y métricas físicas para demostrar la lectura de cadenas.
@Serializable
data class PokemonModel(
    val name: String,
    val height: Int,
    val weight: Int
)