package com.example.pokedex20.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex20.R
import com.example.pokedex20.activities.PrincipalActivity
import com.example.pokedex20.adapters.PokemonAdapter
import com.example.pokedex20.data.PokemonViewModel


class PokeListAlfabeticaFragment : Fragment() {

    private lateinit var v: View
    private lateinit var adaptador: PokemonAdapter
    private lateinit var pokemonRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_poke_list_alfabetica, container, false)

         pokemonRecyclerView = v.findViewById(R.id.pokemonRecyclerViewOrdenado)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val principalActivity = activity as? PrincipalActivity

        // Verifica que la actividad sea de tipo PrincipalActivity y que el adaptador esté inicializado
        if (principalActivity != null && ::adaptador.isInitialized) {
            // Obtén el SearchView de la actividad y configura el OnQueryTextListener
            principalActivity.findViewById<SearchView>(R.id.searchViewPokemon).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        adaptador = PokemonAdapter(PokemonViewModel.filtrarPokemonsPorNombre(query))
                        pokemonRecyclerView.adapter = adaptador
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // Filtrar el adaptador según el nuevo texto
                    if (newText != null) {
                        adaptador = PokemonAdapter(PokemonViewModel.filtrarPokemonsPorNombre(newText))
                        pokemonRecyclerView.adapter = adaptador
                    }
                    return true
                }
            })
        }
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