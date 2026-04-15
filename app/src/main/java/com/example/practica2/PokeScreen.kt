package com.example.practica2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PokeScreen(pokeViewModel: PokeViewModel = viewModel()) {
    // Observa el estado de la API desde el ViewModel
    val textoApi = pokeViewModel.uiState.collectAsState().value

    // Estado local para guardar lo que el usuario escribe en la pantalla
    var textoIngresado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de entrada de texto
        OutlinedTextField(
            value = textoIngresado,
            onValueChange = { textoIngresado = it },
            label = { Text("Escribe un ID o Nombre") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón que dispara la petición de red
        Button(
            onClick = {
                if (textoIngresado.isNotBlank()) {
                    pokeViewModel.obtenerDatosApi(textoIngresado)
                }
            }
        ) {
            Text("Buscar en API")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Texto dinámico con la respuesta
        Text(text = textoApi)
    }
}