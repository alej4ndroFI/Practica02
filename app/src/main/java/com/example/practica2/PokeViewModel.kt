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
        obtenerDatosApi()
    }

    // Ejecuta la petición en un hilo secundario (Corrutina) para evitar el bloqueo del hilo principal (UI Thread).
    private fun obtenerDatosApi() {
        viewModelScope.launch {
            try {
                val resultado = PokeApi.retrofitService.getPokemon()
                _uiState.value = "Elemento obtenido de la API:\nNombre: ${resultado.name.uppercase()}\nAltura: ${resultado.height}\nPeso: ${resultado.weight}"
            } catch (e: Exception) {
                _uiState.value = "Error de conexión: ${e.message}"
            }
        }
    }
}