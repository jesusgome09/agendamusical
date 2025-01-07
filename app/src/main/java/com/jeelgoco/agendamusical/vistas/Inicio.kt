package com.jeelgoco.agendamusical.vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jeelgoco.agendamusical.datos.MyViewModel


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VistaInicioPreview() {
    //VistaInicio(navController = rememberNavController())
}

@Composable
fun VistaInicio(navController: NavController, viewModel: MyViewModel) {

    Column {
        Inicio().PanelInformacion()
        Inicio().PanelListado(navController, viewModel)
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
            Text(text = "Informacion Relevante")
        }

    }


    @Composable
    fun PanelListado(
        navController: NavController, viewModel: MyViewModel
    ) {


        val songTitles by viewModel.songTitles.observeAsState(initial = emptyList())


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()

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
}