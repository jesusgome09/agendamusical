package com.jeelgoco.agendamusical.datos

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MyViewModel(context: Context) : ViewModel() {
    private val dao = AppDatabase.getInstance(context).songDao()


    private val _songTitles = MutableLiveData<List<SongImportant>>(emptyList())
    val songTitles: LiveData<List<SongImportant>> = _songTitles

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Todas las operaciones de la base de datos en un hilo de fondo
                Log.i("MyViewModel", "Paso 1")
                withContext(Dispatchers.IO) {
                    Log.i("MyViewModel", "Paso 2")
                    try {
                        dao.deleteAll()
                    } catch (e: Exception) {
                        Log.e("MyViewModel", "No se pudo eliminar -> $e")

                    }
                    try {
                        dao.insertSong(
                            Song(
                                title = "Aqui estas - Way Maiker",
                                contenido = "Aqui estas, te vemos mover, te adorare, te adorare"
                            )
                        )
                    } catch (e: Exception) {
                        Log.e("MyViewModel", "No se pudo insertar -> $e")

                    }
                    Log.i("MyViewModel", "Paso 3")
                    val importantSongs = dao.getImportante().firstOrNull()
                    _songTitles.postValue(importantSongs) // Actualiza la LiveData en el hilo principal
                }
            } catch (e: Exception) {
                Log.e("MyViewModel","Este es la principal -> $e")
            }
        }
    }
}

