package com.example.practica2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow("Conectando con la API...")
    val uiState: StateFlow<String> = _uiState.asStateFlow()

    init {
        obtenerDatosApi("gengar") // "lucario", "1", "151", etc.
    }

    fun obtenerDatosApi(nombreOId: String) {
        viewModelScope.launch {
            _uiState.value = "Buscando..." // Feedback visual de carga
            try {
                // .lowercase().trim() evita errores si el usuario pone espacios o mayúsculas
                val resultado = PokeApi.retrofitService.getPokemon(nombreOId.lowercase().trim())
                _uiState.value = "Elemento obtenido de la API:\nNombre: ${resultado.name.uppercase()}\nAltura: ${resultado.height}\nPeso: ${resultado.weight}"
            } catch (e: Exception) {
                _uiState.value = "Error: No se encontró el Pokémon o falló la conexión."
            }
        }
    }
}