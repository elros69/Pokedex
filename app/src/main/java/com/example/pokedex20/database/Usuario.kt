package com.example.pokedex20.database

import android.graphics.drawable.Drawable
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey val nombre: String,
    val contraseña: String,
)
