package com.jeelgoco.agendamusical.datos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//DAO es Data Access Object

@Dao
interface SongDao {
    @Query("SELECT * FROM songs")
    fun getAll(): Flow<List<Song>>

    @Query("SELECT * FROM songs")
    fun getImportante(): List<SongImportant>


    @Insert //(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: Song)

    @Query("DELETE FROM songs")
    suspend fun deleteAll()



}