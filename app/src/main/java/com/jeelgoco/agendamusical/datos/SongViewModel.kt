package com.jeelgoco.agendamusical.datos

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel(context: Context) : ViewModel() {
    private val dao = AppDatabase.getInstance(context).songDao()


    private val _songTitles = MutableLiveData<List<Song>>(emptyList())
    val songTitles: MutableLiveData<List<Song>> = _songTitles

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.IO) {


                    val ssong = dao.getAll()
                    _songTitles.postValue(ssong.firstOrNull()) // Actualiza la LiveData en el hilo principal
                }
            } catch (e: Exception) {
                Log.e("MyViewModel", "Este es la principal -> $e")
            }
        }
    }

    fun insertSong(song: Song) {
        viewModelScope.launch(Dispatchers.IO) {

            dao.insertSong(song)
            val cancion = dao.getAll()
            _songTitles.postValue(cancion.firstOrNull())
        }

    }

    fun deleteSong(localSong: Song) {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                dao.deleteSong(localSong.id)
                val cancion = dao.getAll()
                _songTitles.postValue(cancion.firstOrNull())
            } catch (e: Exception) {
                Log.e("MyViewModel", "Este es deleteSong -> $e")
            }
        }

    }
}

