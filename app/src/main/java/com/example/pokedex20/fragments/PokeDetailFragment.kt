package com.example.pokedex20.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pokedex20.R
import com.example.pokedex20.api.PokeAPIClient
import com.example.pokedex20.data.PokemonViewModel
import com.example.pokedex20.model.PokedexEntry
import com.example.pokedex20.model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokeDetailFragment : Fragment() {

    private lateinit var v: View
    private var pokemonSelected: Pokemon? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun update() {
        pokemonSelected = PokemonViewModel.selected
        val imageView = v.findViewById<ImageView>(R.id.detailImageView)
        v.findViewById<TextView>(R.id.detailNameTextView).text = pokemonSelected!!.name
        Glide.with(v)
            .load(pokemonSelected!!.sprites!!.frontDefault)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)

        if (pokemonSelected!!.types.size == 1) {
            v.findViewById<TextView>(R.id.detailTextTypePokemon).text = (pokemonSelected!!.types[0].type!!.name)!!.capitalize()
        } else {
            v.findViewById<TextView>(R.id.detailTextTypePokemon).text = ((pokemonSelected!!.types[0].type!!.name)!!.capitalize() + " | " + (pokemonSelected!!.types[1].type!!.name)!!.capitalize() ?: "nulo")
        }

        val consultaDex = PokeAPIClient.pokeAPIService.getPokemonPokedexEntry(pokemonSelected!!.species!!.url!!)
        consultaDex.enqueue(object : Callback<PokedexEntry> {
            override fun onResponse(call: Call<PokedexEntry>, response: Response<PokedexEntry>) {
                if (response.isSuccessful) {
                    val entradaDex: PokedexEntry? = response.body()
                    v.findViewById<TextView>(R.id.detailDescriptionPokemon).text = entradaDex!!.flavorTextEntries.get(1).flavorText!!
                    //Log.d("Pokedex", "Ha sacado la entrada del pokemon")
                    //Log.d("Pokedex", entradaDex!!.flavorTextEntries.get(1).flavorText!!)

                }
            }

            override fun onFailure(call: Call<PokedexEntry>, t: Throwable) {
                TODO("Not yet implemented")
                t.printStackTrace()
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_poke_detail, container, false)
        this.update()

        return v
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PokeDetailFragment().apply {

            }
    }
}