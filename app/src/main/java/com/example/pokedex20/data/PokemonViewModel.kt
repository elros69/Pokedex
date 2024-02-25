package com.example.pokedex20.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex20.database.Usuario
import com.example.pokedex20.model.Pokemon
import com.example.pokedex20.model.PokemonList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PokemonViewModel(): ViewModel() {

    private val _pokemonList: MutableList<Pokemon> = mutableListOf()


    val pokemonList: List<Pokemon>
        get() = _pokemonList.toList()

    private val _pokemonListLiveData = MutableLiveData<List<Pokemon>>()
    val pokemonListLiveData: LiveData<List<Pokemon>> get() = _pokemonListLiveData

    init {
        viewModelScope.launch {

            // Lanzar la carga de la lista de Pokémon en una corutina bloqueante
            val pokemonRepository = PokemonRepository { listaPokemon ->
                _pokemonListLiveData.value = listaPokemon
                Log.d("LISTA_Dentro", _pokemonList.toString())

            }

        }
        Log.d("LISTA_Fuera", _pokemonList.toString())

    }


    companion object {
        private var _selected: Pokemon? = null
        var selected: Pokemon?
            get() = _selected
            set(item) {
                _selected = item
            }

        var listaPokemons: MutableList<Pokemon> = mutableListOf()
        var listaPokemonOrdenadaId: MutableList<Pokemon> = mutableListOf()

        var loggedInUserInternal: Usuario? = null
            get() = loggedInUserLiveData.value
        private val loggedInUserLiveData: MutableLiveData<Usuario?> = MutableLiveData()

        fun setLoggedInUser(user: Usuario?) {
            loggedInUserLiveData.value = user
        }

        fun getLoggedInUser(): Usuario? {
            return loggedInUserInternal
        }

        fun ordenarPokemonsPorIdDeMenorAMayor(): MutableList<Pokemon> {
            listaPokemonOrdenadaId = listaPokemons.sortedBy { it.id ?: 0 }.toMutableList()
            return listaPokemonOrdenadaId
        }

    }







}