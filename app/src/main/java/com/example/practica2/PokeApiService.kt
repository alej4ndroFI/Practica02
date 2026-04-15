package com.example.practica2

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://pokeapi.co/api/v2/"
// Cliente de red genérico. Se ignora la metadata no mapeada en el modelo para ahorrar memoria.
private val json = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface PokeApiService {
    // Las llaves {id} indican que es un valor dinámico
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") pokemonIdOrName: String): PokemonModel
}

object PokeApi {
    val retrofitService: PokeApiService by lazy {
        retrofit.create(PokeApiService::class.java)
    }
}