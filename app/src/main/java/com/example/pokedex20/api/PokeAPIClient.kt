package com.example.pokedex20.api

class PokeAPIClient {

    companion object {
        val pokeAPIService: PokeAPIService by lazy {
            RetrofitClient.retrofit.create(PokeAPIService::class.java)
        }
    }

}