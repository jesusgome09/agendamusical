package com.jeelgoco.agendamusical.datos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun Sincronizador(
    localViewModel: MyViewModel,
    firebaseViewModel: SongListViewModel,
) {
    val localSongs by localViewModel.songTitles.observeAsState(initial = emptyList())
    val firebaseSongs by firebaseViewModel.songs.collectAsState(initial = emptyList())

    //exploramos la base de datos local comparando las id's con la db online de tal modo
    // que si no lo encuentra lo inserta
    for (fireSong in firebaseSongs) {
        var found = false
        for (localSong in localSongs!!) {
            if (localSong.id == fireSong.id) {
                found = true
                break
            }
        }
        if (!found) {
            fireSong.creador?.let {
                fireSong.id?.let { it1 ->
                    fireSong.titulo?.let { it2 ->
                        fireSong.contenido?.let { it3 ->
                            Song(
                                id = it1, title = it2, contenido = it3, creador = it
                            )
                        }
                    }
                }
            }?.let { localViewModel.insertSong(it) }
        }
    }

    for (localSong in localSongs!!) {
        var found = false
        for (fireSong in firebaseSongs) {
            if (localSong.id == fireSong.id) {
                found = true
                break
            }
        }
        if (!found) {
            //localViewModel.deleteSong(localSong)
            //lo de abajo es temporal
            firebaseViewModel.addSong(
                SongF(
                    localSong.id,
                    localSong.title,
                    localSong.contenido,
                    localSong.creador
                )
            )
        }
    }
}



