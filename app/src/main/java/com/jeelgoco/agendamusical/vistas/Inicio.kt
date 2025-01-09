package com.jeelgoco.agendamusical.vistas

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jeelgoco.agendamusical.R
import com.jeelgoco.agendamusical.datos.MyViewModel
import com.jeelgoco.agendamusical.datos.Sincronizar
import com.jeelgoco.agendamusical.datos.Song
import com.jeelgoco.agendamusical.datos.SongListViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VistaInicio(navController: NavController, myViewModel: MyViewModel) {

    val firebaseViewModel: SongListViewModel = viewModel()
    Sincronizar(localViewModel = myViewModel, firebaseViewModel = firebaseViewModel)


    Scaffold(
        content = {
        Inicio().VistaCompuesta(
            navController, myViewModel, modifier = Modifier.padding(it)
        )
    },
        floatingActionButton = {
            Sobrepuestos().FabAdd()
        },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.Center,
        bottomBar = { Sobrepuestos().BotonDebajo() }
    )

}


private class Inicio {

    @Composable
    fun VistaCompuesta(navController: NavController, myViewModel: MyViewModel, modifier: Modifier) {

        Column(modifier = modifier
            .background( Color(0xFFB0A4FD))
        ) {
            Inicio().Saludo(name = "Jesus")
            Inicio().PanelVerso()
            Inicio().TextoIntermedio()
            Inicio().PanelListado(navController, myViewModel)
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
                .defaultMinSize(minHeight = 250.dp)

        ) {


            items(songTitles ?: emptyList()) { cancion ->
                CardBoton(titulo = cancion.title, navController = navController, cancion.id)
            }


        }
    }

    @Composable
    fun CardBoton(titulo: String, navController: NavController, id: Int) {


        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, start = 15.dp, end = 15.dp)
            .clickable { navController.navigate("Cancion/$id") }
            .height(50.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp


            )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                //icono de musica
                Image(
                    painter = painterResource(id = R.drawable.icon_musicc),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(40.dp)
                        .padding(0.dp)

                )
                Text(
                    text = titulo, modifier = Modifier.align(Alignment.Center), fontSize = 20.sp
                )
            }


        }
    }

    @Composable
    fun Saludo(name: String) {
        Text(
            text = "Hello, $name! 👋", fontWeight = FontWeight.Bold, fontSize = 25.sp,

            modifier = Modifier.padding(top = 18.dp, start = 10.dp, end = 10.dp)
        )
    }

    @Composable
    fun PanelVerso() {
        Box(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 15.dp)
                .height(90.dp)
        ) {
            Text(
                text = "Todo lo puedo en cristo que me fortaleze",
                fontSize = 20.sp,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
            Text(
                text = "Filipenses 4:13",
                fontSize = 15.sp,
                modifier = Modifier.align(alignment = Alignment.BottomEnd)
            )
        }
    }

    @Composable
    fun TextoIntermedio() {
        Text(
            text = "Canciónes",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 18.dp, start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

//TODO Esto es para probar la vista


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VistaPrevia() {

    Scaffold(
        content = {
            Compilado(modifier = Modifier.padding(it))
        },
        floatingActionButton = {
            Sobrepuestos().FabAdd()
        },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.Center,
        bottomBar = { Sobrepuestos().BotonDebajo() }
    )


}


@SuppressLint("InvalidColorHexValue")
@Composable
fun Compilado(modifier: Modifier) {
    Column(
        modifier = modifier
            .background( Color(0xFFB0A4FD))



    ) {
        Saludo("Jesus")
        PanelVerso()
        TextoIntermedio()
        prueba().PanelListado()
    }
}

@Composable
fun Saludo(name: String) {
    Text(
        text = "Hello, $name! 👋", fontWeight = FontWeight.Bold, fontSize = 25.sp,

        modifier = Modifier.padding(top = 18.dp, start = 10.dp, end = 10.dp)
    )
}

@Composable
fun PanelVerso() {
    Box(
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 15.dp)
            .height(90.dp)
    ) {
        Text(
            text = "Todo lo puedo en cristo que me fortaleze",
            fontSize = 20.sp,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
        Text(
            text = "Filipenses 4:13",
            fontSize = 15.sp,
            modifier = Modifier.align(alignment = Alignment.BottomEnd)
        )
    }
}

@Composable
fun TextoIntermedio() {
    Text(
        text = "Canciónes",
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 18.dp, start = 10.dp, end = 10.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
}


class prueba {

    @Composable
    fun PanelListado(

    ) {


        val songTitles: List<Song> = listOf(
            Song(1, "Cancion 1", "Contenido 1", "Creador 1"),
            Song(2, "Cancion 2", "Contenido 2", "Creador 2"),

            )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .defaultMinSize(minHeight = 250.dp)

        ) {


            items(songTitles) { cancion ->
                CardBoton(titulo = cancion.title, cancion.id)
            }


        }
    }

    @Composable
    fun CardBoton(titulo: String, id: Int) {


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
                .height(50.dp)
                .clickable { },
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )


        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                //icono de musica
                Image(
                    painter = painterResource(id = R.drawable.icon_musicc),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(40.dp)
                        .padding(0.dp)

                )
                Text(
                    text = titulo, modifier = Modifier.align(Alignment.Center), fontSize = 20.sp
                )
            }


        }
    }
}