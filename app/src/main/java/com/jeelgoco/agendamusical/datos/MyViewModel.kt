package com.jeelgoco.agendamusical.datos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeelgoco.agendamusical.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyViewModel(context: MainActivity) : ViewModel() {

    private val dao = AppDatabase.getInstance(context).songDao() // Acceso correcto a SongDao
    private val _songTitles = MutableLiveData<List<SongImportant>>()
    val songTitles: LiveData<List<SongImportant>> = _songTitles


    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                //insertar
                dao.deleteAll()
                dao.insertSong(
                    Song(
                        title = "Aqui estas - Way Maiker",
                        contenido = "Aqui estas, te vemos mover, te adorare, te adorare",
                        previewPath = 106515
                    )
                )


                val songTitles = dao.getImportante()
                _songTitles.postValue(songTitles)
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }



}
