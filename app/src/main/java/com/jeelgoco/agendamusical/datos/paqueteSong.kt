package com.jeelgoco.agendamusical.datos

import android.content.Context
import android.util.Log
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "canciones") //indica que esta clase representa una tabla de base de datos
data class Song(
    @PrimaryKey() //indica que el campo id es la clave primaria y se genera automáticamente
    val id: Int,
    val title: String,
    val contenido: String,
    val creador: String, // nueva columna


)

data class SongF(
    var id: Int? = null,
    var titulo: String? = null,
    var contenido: String? = null,
    var creador: String? = null
) {
    constructor() : this(null, null, null, null)
}

//DAO es Data Access Object

@Dao
interface SongDao {
    @Query("SELECT * FROM canciones")
    fun getAll(): Flow<List<Song>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: Song)

    @Query("DELETE FROM canciones")
    suspend fun deleteAll()

    @Query("DELETE FROM canciones WHERE id = :id")
    suspend fun deleteSong(id: Int)

}


@Database(entities = [Song::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase { // Tipode retorno correcto
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addMigrations(Migration1To2())
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Log.d(
                                "MyViewModel",
                                "onCreate: La base de datos y las tablas han sido creadas"
                            )
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class Migration1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Aquí va el código para migrar la base de datos
        database.execSQL("ALTER TABLE canciones ADD COLUMN creador TEXT NOT NULL DEFAULT ''")
    }
}

