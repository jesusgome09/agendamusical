package com.jeelgoco.agendamusical.vistas

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jeelgoco.agendamusical.datos.MyViewModel
import com.jeelgoco.agendamusical.datos.Song


@Composable
fun VistaCancion(navController: NavController, id: Int, viewModel: MyViewModel) {


    val song by viewModel.songTitles.observeAsState(initial = emptyList())

    Letras().Main(navController, song, id)

}


class Letras {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun Main(navController: NavController, song: List<Song>, id: Int) {

        var titulo = " NA"
        var contenido = " NA"
        var creador = " NA"

        for (i in song) {
            if (i.id == id) {
                titulo = i.title
                contenido = i.contenido
                creador = i.creador
            }
        }

        Scaffold(
            topBar = { TopBar(navController = navController, titulo) },
            content = { Contenido(contenido) }

        )

    }

    @SuppressLint("RestrictedApi")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar(navController: NavController, titulo: String) {


        TopAppBar(
            title = { Text(text = titulo) },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("Inicio") }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            modifier = Modifier
                .shadow(elevation = 4.dp),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Gray)
        )

    }

    @Composable
    fun Contenido(contenido: String) {
        Text(text = contenido)

    }


}