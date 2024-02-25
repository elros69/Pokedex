package com.example.pokedex20.database

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters(private val context: Context) {


        // Convierte un Drawable a String
        @TypeConverter
        fun drawableToString(drawable: Drawable?): String? {
            return Gson().toJson(drawable)
        }

        @TypeConverter
        fun stringToDrawable(value: String?): Drawable? {
        if (value == null) {
            return null
        }
        val resourceId = value.toIntOrNull()
        return if (resourceId != null) {
            // Obtener el Drawable a partir del ID de recurso
            ContextCompat.getDrawable(context, resourceId)
        } else {
            null
        }
    }

}