package com.jeelgoco.agendamusical.vistas

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeelgoco.agendamusical.MainActivity
import com.jeelgoco.agendamusical.datos.MyViewModel
import com.jeelgoco.agendamusical.datos.SongFireBase
import com.jeelgoco.agendamusical.datos.SongListViewModel




@Composable
fun VistaInicio(navController: NavController, myViewModel: MyViewModel) {

    val firebaseViewModel : SongListViewModel = viewModel()

    Column {
        Inicio().PanelInformacion()
        Inicio().PanelListado(navController, myViewModel)
        Inicio().ViewSong(firebaseViewModel)
    }

}


private class Inicio {


    @Composable
    fun PanelInformacion() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.Green)

        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Bienvenido a la agenda musical",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp
                )
            }
        }

    }


    @Composable
    fun PanelListado(
        navController: NavController, viewModel: MyViewModel
    ) {


        val songTitles by viewModel.songTitles.observeAsState(initial = emptyList())


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 250.dp)

        ) {


            items(songTitles) { cancion ->
                CardBoton(titulo = cancion.title, navController = navController, cancion.id)
            }


        }
    }

    @Composable
    fun CardBoton(titulo: String, navController: NavController, id: Int) {


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                .clickable { navController.navigate("Cancion/$id") }


        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                Text(
                    text = titulo,
                    modifier = Modifier.align(Alignment.Center)
                )
            }


        }
    }


    @Composable
    fun ViewSong(viewModel: SongListViewModel){
        val songs by viewModel.songs.collectAsState()
        Log.i("FireBase", "Lista: $songs")

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .defaultMinSize(minHeight = 100.dp)
        ) {
            item {
                Text(text = "--------Canciones-------")
            }
            items(songs) { song ->
                Column(modifier = Modifier.background(Color.Cyan)){
                    Text(text = song.titulo)
                    Text(text = song.contenido)
                    Text(text = song.creador)

                    if(songs.isEmpty()){
                        Text(text = "NA")
                        Text(text = "NA")
                        Text(text = "NA")

                    }
                }
            }
        }

    }
}

