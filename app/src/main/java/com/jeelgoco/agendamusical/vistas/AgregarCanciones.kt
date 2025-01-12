package com.jeelgoco.agendamusical.vistas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeelgoco.agendamusical.datos.MyViewModel
import com.jeelgoco.agendamusical.datos.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AgregarCanciones {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ExpandableBottomShet(
        localViewModel: MyViewModel,
        sheetState: SheetState,
        corroutineScope: CoroutineScope,
        onDismiss: () -> Unit
    ) {


        ModalBottomSheet(

            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            contentWindowInsets = { WindowInsets(0) },
            onDismissRequest = {
                corroutineScope.launch {
                    sheetState.hide()
                    onDismiss()
                }
            }
        ) {
            AgregarCancion(localViewModel, corroutineScope, sheetState) {
                onDismiss()
            }
        }


    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AgregarCancion(
        localViewModel: MyViewModel,
        coroutineScope: CoroutineScope,
        sheetState: SheetState,
        onDimiss: () -> Unit
    ) {
        var titulo by remember { mutableStateOf("") }
        var creador by remember { mutableStateOf("") }
        var contenido by remember { mutableStateOf("") }
        var pase by remember { mutableStateOf(false) }

        Column(Modifier.background(Color(0xFFB0A4FD))) {
            Titulo("Agregar Cancion")
            TextoS("Titulo")
            CajaDeTexto(label = "Titulo", placeholder = "Eres Todo Poderoso") { titulo = it }
            TextoS("Creador")
            CajaDeTexto(label = "Creador", placeholder = "Marcos Barrientos") { creador = it }
            TextoS("Contenido")
            CajaDeTexto(label = "Contenido", placeholder = "La unica razón...") { contenido = it }

            var enviado by remember { mutableStateOf(false) }
            var id = 0
            do {

                id = IntRange(1, 10000).random()
            } while (!compobrarId(localViewModel, id))

            pase = titulo.length > 4 && creador.length > 4 && contenido.length > 4

            BotonAgregar(localViewModel, Song(id, titulo, contenido, creador), pase) {
                enviado = true
            }
            if (enviado) {
                Text(
                    text = "Se ah creado correctamente.",
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
                )
                coroutineScope.launch {
                    sheetState.hide()
                    onDimiss()
                }


            }


        }
    }

    @Composable
    fun compobrarId(localViewModel: MyViewModel, id: Int): Boolean {

        val localSongs by localViewModel.songTitles.observeAsState(initial = emptyList())
        for (song in localSongs) {
            if (song.id == id) {
                return false
            }

        }
        return true
    }

    @Composable
    fun BotonAgregar(
        localViewModel: MyViewModel,
        song: Song,
        paso: Boolean,
        onDismiss: () -> Unit
    ) {
        Box(Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    localViewModel.insertSong(song)
                    onDismiss()
                },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                enabled = paso
            ) {
                Box(modifier = Modifier.fillMaxWidth(0.3f)) {
                    Text(text = "Agregar", modifier = Modifier.align(Alignment.TopStart))
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.TopEnd)
                    )
                }

            }
        }
    }

    @Composable
    fun CajaDeTexto(label: String, placeholder: String, onDismiss: (it: String) -> Unit) {
        var texto by remember { mutableStateOf("") }
        TextField(
            value = texto,
            onValueChange = {
                texto = it
                onDismiss(texto)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 18.dp),
            placeholder = { Text(placeholder) },
            singleLine = label != "Contenido",
            isError = texto.length < 4,


            )
    }

    @Composable
    fun TextoS(texto: String) {
        Text(
            text = texto,
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 18.dp),
            fontSize = 20.sp,
        )
    }

    @Composable
    fun Titulo(texto: String) {
        Text(
            text = texto,
            modifier = Modifier
                .padding(16.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )


    }
}