package com.example.pokedex20.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    // Le indico que todo lo aqui dentro creado sera estatico, es decir, Singleton
    companion object {

        // URL base del objeto retrofit
        val BASE_URL: String = "https://pokeapi.co/api/v2/"

        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}