package com.jeelgoco.agendamusical.datos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//DAO es Data Access Object

@Dao
interface SongDao {
    @Query("SELECT * FROM canciones")
    fun getAll(): Flow<List<Song>>

    @Query("SELECT * FROM canciones")
    fun getImportante(): Flow<List<SongImportant>>


    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: Song)

    @Query("DELETE FROM canciones")
    suspend fun deleteAll()

}