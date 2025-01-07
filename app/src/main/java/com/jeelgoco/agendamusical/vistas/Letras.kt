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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jeelgoco.agendamusical.MainActivity
import com.jeelgoco.agendamusical.datos.MyViewModel
import com.jeelgoco.agendamusical.datos.SongImportant


@Composable
fun VistaCancion(navController: NavController, id: Int, viewModel: MyViewModel) {



    val song by viewModel.songTitles.observeAsState(initial = emptyList())

    Letras().Main(navController, song, id)

}


class Letras {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun Main(navController: NavController, song: List<SongImportant>, id: Int) {
        Scaffold(
            topBar = { TopBar(navController = navController, song, id) },
            content = { Contenido(song, id) }

        )

    }

    @SuppressLint("RestrictedApi")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar(navController: NavController, song: List<SongImportant>, id: Int) {
        TopAppBar(
            title = { Text(text = song[id].title) },
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
    fun Contenido(song: List<SongImportant>, id: Int) {
        Text(text = song[id].contenido)

    }


}