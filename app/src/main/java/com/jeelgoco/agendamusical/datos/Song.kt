package com.jeelgoco.agendamusical.datos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs") //indica que esta clase representa una tabla de base de datos
data class Song (
    @PrimaryKey(autoGenerate = true) //indica que el campo id es la clave primaria y se genera automáticamente
    val id: Int = 0,
    val title: String,
    val contenido: String,
    val previewPath: Int, //ruta del archivo


    )

data class SongImportant(
    val title: String,
    val previewPath: Int,
    val contenido: String,
    val id: Int ,
)


