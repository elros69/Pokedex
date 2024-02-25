package com.example.pokedex20.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsuarioDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE nombre = :nombre")
    suspend fun obtenerUsuario(nombre: String): Usuario?

    @Update
    suspend fun actualizarUsuario(usuario: Usuario)

}