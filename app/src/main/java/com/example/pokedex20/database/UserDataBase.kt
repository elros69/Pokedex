package com.example.pokedex20.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Usuario::class], version = 4, exportSchema = false)
abstract class UserDataBase(): RoomDatabase() {

    abstract fun usuarioDAO(): UsuarioDAO

    companion object {

        private var instance: UserDataBase? = null

        private val roomCallBack = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }

        @Synchronized
        fun getInstance(ctx: Context): UserDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    ctx.applicationContext, UserDataBase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(roomCallBack)
                    .build()
            }

            return instance!!
        }
    }

}