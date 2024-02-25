package com.example.pokedex20.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex20.R
import com.example.pokedex20.adapters.PokemonAdapter
import com.example.pokedex20.data.PokemonViewModel


class PokeListAlfabeticaFragment : Fragment() {

    private lateinit var v: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_poke_list_alfabetica, container, false)

        val pokemonRecyclerView: RecyclerView = v.findViewById(R.id.pokemonRecyclerViewOrdenado)
        var adaptador: PokemonAdapter
        adaptador = PokemonAdapter(PokemonViewModel.listaPokemonOrdenadaNombre)
        adaptador.click = { position, pokemon ->
            PokemonViewModel.selected = pokemon
            val fm: FragmentManager = parentFragmentManager
            fm.commit {
                setCustomAnimations(
                    R.anim.slide_in_from_right,  // Animación de entrada
                    R.anim.slide_out_to_left,     // Animación de salida
                    R.anim.slide_in_from_left,    // Animación de entrada para el nuevo fragmento
                    R.anim.slide_out_to_right     // Animación de salida para el fragmento actual
                )
                replace(R.id.fragmentContainerView, PokeDetailFragment.newInstance())
                addToBackStack("replacement")
            }
        }
        // Esto de aqui
        val layoutManager = GridLayoutManager(this.context, 1)
        pokemonRecyclerView.layoutManager = layoutManager
        pokemonRecyclerView.adapter = adaptador

        return v
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PokeListAlfabeticaFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}