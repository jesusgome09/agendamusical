package com.jeelgoco.agendamusical.vistas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeelgoco.agendamusical.datos.MyViewModel
import com.jeelgoco.agendamusical.datos.Song


@Composable
fun VistaCancion(navController: NavController, id: Int, viewModel: MyViewModel) {


    val song by viewModel.songTitles.observeAsState(initial = emptyList())

    song?.let { Letras().Main(navController, it, id) }

}


@Composable
fun vistaprevia() {
    val navController = rememberNavController()
    val song = listOf(
        Song(
            1,
            "Cancion 1",
            "Aquí estás \\nTe vemos mover \\nTe adoraré \\nTe adoraré  Aquí estás Obrando en mí Te adoraré Te adoraré  Aquí estás Te vemos mover Te adoraré Te adoraré  Aquí estás Obrando en mí Te adoraré Te adoraré  (Y Te llamamos) Milagroso, abres camino Cumples promesas Luz en tinieblas Mi Dios, así eres Tú  Milagroso, abres camino Cumples promesas Luz en tinieblas Mi Dios, así eres Tú  Aquí estás Sanando mi corazón Te adoraré Te adoraré  Aquí estás Tocando mi corazón Te adoraré Te adoraré  (Te llamamos) Milagroso, abres camino Cumples promesas Luz en tinieblas Mi Dios, así eres Tú  Milagroso, abres camino Cumples promesas Luz en tinieblas Mi Dios, así eres Tú  Aquí estás Tocando mi corazón Te adoraré Te adoraré  (Aquí estás) aquí estás Sanando mi corazón Te adoraré Te adoraré  (Hoy y siempre, Te llamamos) Milagroso, abres camino Cumples promesas Luz en tinieblas Mi Dios, así eres Tú  Milagroso, abres camino Cumples promesas Luz en tinieblas Mi Dios, así eres Tú  Así eres Tú (así eres Tú) Así eres Tú (así eres Tú) Así eres Tú (así eres Tú) Así eres Tú (así eres Tú)  Aunque no pueda ver, estás obrando Aunque no pueda ver, estás obrando Siempre estás, siempre estás obrando Siempre estás, siempre estás obrando  Aunque no pueda ver, estás obrando Aunque no pueda ver, estás obrando Siempre estás, siempre estás obrando Siempre estás, siempre estás obrando  Aunque no pueda ver, estás obrando Aunque no pueda ver, estás obrando Siempre estás, siempre estás obrando Siempre estás, siempre estás obrando  Aunque no pueda ver, estás obrando Aunque no pueda ver, estás obrando Siempre estás, siempre estás obrando Siempre estás, siempre estás obrando  Milagroso, abres camino Cumples promesas Luz en tinieblas Mi Dios, así eres Tú  Milagroso, abres camino Cumples promesas Luz en tinieblas Mi Dios, así eres Tú  Milagroso, abres camino Cumples promesas Luz en tinieblas Mi Dios, así eres Tú Oh  Milagroso, abres camino Cumples promesas Luz en tinieblas Mi Dios, así eres Tú Oh  Solo así eres Tú (así eres Tú) Así eres Tú (así eres Tú) Así eres Tú (así eres Tú) Así eres Tú (así eres Tú)".trimIndent(),
            "Creador 1"
        ),
        Song(2, "Cancion 2", "Contenido 2", "Creador 2"),
    )
    val id = 1
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
            content = {
                Contenido(
                    contenido, modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                )
            }

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

        )

    }

    @Composable
    fun Contenido(contenido: String, modifier: Modifier) {


        val texto = contenido.replace("\\n", "\n")
        LazyColumn(modifier = modifier) {


            item {
                Text(
                    text = texto,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 7.dp),
                    fontSize = 20.sp
                )

            }
        }

    }


}