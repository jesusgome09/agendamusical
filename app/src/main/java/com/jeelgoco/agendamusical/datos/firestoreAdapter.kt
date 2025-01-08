package com.jeelgoco.agendamusical.datos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class SongRepository {
    private val db = FirebaseFirestore.getInstance()
    private val songCollection = db.collection("canciones")


    suspend fun getSongs(): List<SongFireBase> {
        return try {
            val snapshot = songCollection.get().await()

            snapshot.documents.mapNotNull { doc ->
                doc.toObject(SongFireBase::class.java)
            }
        } catch (e: Exception) {
            Log.i("FireBase", "Error en getSongs: $e")
            emptyList()
        }
    }

    suspend fun addSong(song: SongFireBase) {
        try {
            songCollection.document(song.id.toString()).set(song).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getSongRealTime(callback: (List<SongFireBase>) -> Unit) {
        songCollection.addSnapshotListener { snapshot, e ->
            if (snapshot != null) {
                val songs = snapshot.documents.mapNotNull {
                    it.toObject(SongFireBase::class.java)
                }
                callback(songs)
            }
        }

    }
}

class SongListViewModel : ViewModel() {
    private val repository = SongRepository()
    private val _songs = MutableStateFlow<List<SongFireBase>>(emptyList())
    val songs: StateFlow<List<SongFireBase>> = _songs

    init {
        fetchSongs()
    }

    private fun fetchSongs() {
        Firebase.firestore.collection("canciones")
            .addSnapshotListener{snapshot, error ->

                if (error != null) {
                    Log.i("FireBase", "Error en fetchSongs: $error")
                    return@addSnapshotListener
                }

                val songList = snapshot?.documents?.mapNotNull {doc ->
                    doc.toObject(SongFireBase::class.java)
                } ?: emptyList()

                _songs.value = songList //actualiza la lista de canciones

    }
        viewModelScope.launch {
            val fetchedSongs = repository.getSongs()
            _songs.value = repository.getSongs()
        }
    }

    fun addSong(song: SongFireBase) {
        viewModelScope.launch {
            repository.addSong(song)
            fetchSongs()
        }
    }

}

data class SongFireBase(
    val id: Int = 0,
    val titulo: String = "",
    val contenido: String = "",
    val creador: String = ""

)