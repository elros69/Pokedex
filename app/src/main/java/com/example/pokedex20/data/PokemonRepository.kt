package com.example.pokedex20.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokedex20.api.PokeAPIClient
import com.example.pokedex20.model.Pokemon
import com.example.pokedex20.model.PokemonList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository(private val onPokemonListReady: (MutableList<Pokemon>) -> Unit) {

        private val _pokemonListLiveData = MutableLiveData<List<Pokemon>>()
        val pokemonListLiveData: LiveData<List<Pokemon>> get() = _pokemonListLiveData

        var listaPokemons: MutableList<Pokemon> = mutableListOf()
        val respuesta = PokeAPIClient.pokeAPIService.getPokemonList()
        var list: PokemonList? = null

        init {
            respuesta.enqueue(object : Callback<PokemonList> {
                override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                    if (response.isSuccessful) {
                        // Procesar la respuesta aquí

                        val listaProvisional: PokemonList? = response.body()
                        list = listaProvisional

                        //Log.d("Prueba", "a entrao")
                        Log.d("METIO_LISTA", listaProvisional.toString())

                        // Comprobamos que la lista no este vacia
                        if (listaProvisional != null && listaProvisional.results.isNotEmpty()) {

                            // Si no esta vacia obtenemos cada Pokemon
                            for (pokemon in listaProvisional.results) {

                                // De cada pokemon extraemos su URL y la metemos en la funcion GET
                                val serviceGetPokemon = PokeAPIClient.pokeAPIService.getPokemonDetails(pokemon.url!!)

                                // Ejecutamos el metodo de manera asincrona
                                serviceGetPokemon.enqueue(object : Callback<Pokemon> {

                                    override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                                        val pokemon: Pokemon? = response.body()
                                        if (pokemon != null) {
                                            if (listaPokemons.add(pokemon)) {
                                                PokemonViewModel.listaPokemons.add(pokemon)
                                                Log.d("METIO", pokemon.name!!)
                                            }
                                        }
                                        if (listaPokemons.size == listaProvisional.results.size) {
                                            onPokemonListReady.invoke(listaPokemons)
                                            _pokemonListLiveData.postValue(listaPokemons)
                                        }

                                        //Log.d("Prueba", "Un pokemon hecho")
                                        //Log.d("Prueba", pokemon.toString())
                                    }

                                    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                                        Log.e("Prueba", "Error en la respuesta: ${response.code()}")
                                    }
                                })
                            }


                        }

                    } else {
                        // Manejar respuesta no exitosa
                        Log.e("Prueba", "Error en la respuesta: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                    // Manejar fallo en la llamada
                    t.printStackTrace()
                }
            })
        }

        fun getListaPokemon(): MutableList<Pokemon> {
            return listaPokemons
        }

        fun notifyPokemonListReady() {
            onPokemonListReady.invoke(listaPokemons)
            _pokemonListLiveData.postValue(listaPokemons)
        }



}