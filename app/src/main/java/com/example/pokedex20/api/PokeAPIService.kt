package com.example.pokedex20.api

import com.example.pokedex20.model.PokedexEntry
import com.example.pokedex20.model.Pokemon
import com.example.pokedex20.model.PokemonList
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PokeAPIService {

    // Funcion GET encargada de pedir y recibir la lista de los primeros 151 pokemons
    @GET("pokemon?limit=151&offset=0")
    fun getPokemonList(): Call<PokemonList>

    @GET
    fun getPokemonDetails(@Url url: String): Call<Pokemon>

    @GET
    fun getPokemonPokedexEntry(@Url url: String): Call<PokedexEntry>

}