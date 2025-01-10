package com.jeelgoco.agendamusical.vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jeelgoco.agendamusical.datos.MyViewModel

class Busqueda {


    @Composable
    fun VistaBusqueda(viewModel: MyViewModel, navController: NavController) {
        BarraSearch(viewModel, navController)
    }

    @Composable
    fun BarraSearch(viewModel: MyViewModel, navController: NavController) {


        var texto by remember { mutableStateOf("") } //estado de busqueda
        val cancioness = viewModel.songTitles.observeAsState(initial = emptyList())
        val filteredSongs = cancioness.value.filter {
            it.title.contains(
                texto,
                ignoreCase = true
            ) || texto.isBlank()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFB0A4FD))
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = texto,
                onValueChange = { texto = it },
                label = { Text("Buscar") },
                modifier = Modifier.fillMaxWidth().padding(18.dp),
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredSongs) { song ->
                    Inicio().CardBoton(titulo = song.title, navController, song.id)
                }
            }

        }
    }
}