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


    private val _songTitles = MutableLiveData<List<Song>>(emptyList())
    val songTitles: LiveData<List<Song>> = _songTitles

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.IO) {

                    Log.i("MyViewModel", "Paso 3")
                    val importantSongs = dao.getAll().firstOrNull()
                    _songTitles.postValue(importantSongs) // Actualiza la LiveData en el hilo principal
                }
            } catch (e: Exception) {
                Log.e("MyViewModel","Este es la principal -> $e")
            }
        }
    }
}

