package com.example.pokedex20.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pokedex20.R
import com.example.pokedex20.model.Pokemon

class PokemonAdapter(private val listaPokemon: List<Pokemon>): Adapter<PokemonAdapter.PokemonViewHolder>() {

    var click: ((Int, Pokemon) -> Unit)? = null

    inner class PokemonViewHolder(internal val view: View): ViewHolder(view) {
        // Pones los objetos de la vista: Texfields y todas esas movidas.

        internal val pokemonNumeroTextCardView: TextView = view.findViewById(R.id.pokemonNumeroTextCardView)
        internal val pokemonNombreTextCardView: TextView = view.findViewById(R.id.pokemonNombreTextCardView)
        internal val pokemonTiposTextCardView: TextView = view.findViewById(R.id.pokemonTiposTextCardView)
        internal val pokemonCardImageView: ImageView = view.findViewById(R.id.pokemonCardImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pokemon_layout, parent, false))
    }

    override fun getItemCount(): Int =listaPokemon.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        // Esta funcion te pasa la posicion del pokemon que se esta referenciando en la lista
        // asi que solo has de llamarlo a traves de la lista.
        //Despues en alholder le pones los atributos de las vistas y los listener de los botones

        Glide.with(holder.view)
            .load(listaPokemon[position].sprites!!.frontDefault)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.pokemonCardImageView)

        holder.pokemonNombreTextCardView.text = listaPokemon[position].name!!.capitalize()
        holder.pokemonNumeroTextCardView.text = listaPokemon[position].id.toString()
        if (listaPokemon[position].types.size == 1) {
                holder.pokemonTiposTextCardView.text = (listaPokemon[position].types[0].type!!.name)!!.capitalize()
        } else {
            holder.pokemonTiposTextCardView.text = ((listaPokemon[position].types[0].type!!.name)!!.capitalize() + " | " + (listaPokemon[position].types[1].type?.name!!.capitalize() ?: "nulo"))
        }


    }

}