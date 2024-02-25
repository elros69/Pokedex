package com.example.pokedex20.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pokedex20.api.PokeAPIClient
import com.example.pokedex20.data.PokemonRepository
import com.example.pokedex20.data.PokemonViewModel
import com.example.pokedex20.model.PokedexEntry
import com.example.pokedex20.model.Pokemon
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import com.example.pokedex20.R

class MainActivity : AppCompatActivity() {

    private val viewModel: PokemonViewModel = PokemonViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaPok: MutableList<Pokemon> = mutableListOf()

        /*val pokemonRepository = PokemonRepository { listaPokemon ->

            var requestDex: Call<PokedexEntry>? = null
            if (listaPokemon != null) {
                requestDex = PokeAPIClient.pokeAPIService.getPokemonPokedexEntry(listaPokemon.get(1).species!!.url!!)
                requestDex.enqueue(object : Callback<PokedexEntry> {
                    override fun onResponse(call: Call<PokedexEntry>, response: Response<PokedexEntry>) {
                        if (response.isSuccessful) {
                            val entradaDex: PokedexEntry? = response.body()

                            Log.d("Pokedex", "Ha sacado la entrada del pokemon")
                            Log.d("Pokedex", entradaDex!!.flavorTextEntries.get(1).flavorText!!)

                        }
                    }

                    override fun onFailure(call: Call<PokedexEntry>, t: Throwable) {
                        TODO("Not yet implemented")
                        t.printStackTrace()
                    }
                })
            } else {
                Log.d("MAL", "sigue vacia tu")
            }/*


        }







        // Esto ya te llena por completo la clase de PokemonList



        //Log.d("LISTA", pokemonRepository.getListaPokemon().toString())*/



*/

    }
}