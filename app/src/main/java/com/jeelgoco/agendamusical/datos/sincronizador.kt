package com.jeelgoco.agendamusical.datos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun Sincronizar(localViewModel: MyViewModel, firebaseViewModel: SongListViewModel) {

    val localSongs by localViewModel.songTitles.observeAsState(initial = emptyList())
    val firebaseSongs by firebaseViewModel.songs.collectAsState(initial = emptyList())

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
                                id = it1,
                                title = it2,
                                contenido = it3,
                                creador = it
                            )
                        }
                    }
                }
            }
                ?.let { localViewModel.insertSong(it) }
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
            localViewModel.deleteSong(localSong)
        }
    }

}

