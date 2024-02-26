package com.example.pokedex20.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.FragmentContainerView
import retrofit2.Callback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex20.R
import com.example.pokedex20.activities.PrincipalActivity
import com.example.pokedex20.adapters.PokemonAdapter
import com.example.pokedex20.api.PokeAPIClient
import com.example.pokedex20.data.PokemonRepository
import com.example.pokedex20.data.PokemonViewModel
import com.example.pokedex20.model.Pokemon
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response


class PokeListFragment : Fragment() {

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
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_poke_list, container, false)

         pokemonRecyclerView = v.findViewById(R.id.pokemonRecyclerView)




        adaptador = PokemonAdapter(PokemonViewModel.listaPokemonOrdenadaId)
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

    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        val principalActivity = context as PrincipalActivity
        principalActivity.findViewById<SearchView>(R.id.searchViewPokemon).setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adaptador.filter.filter(newText)
                return true
            }
        })

    }*/


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
                    Log.d("Hola", "Si le doy a submit hace esto")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // Filtrar el adaptador según el nuevo texto
                    if (newText != null) {
                        adaptador = PokemonAdapter(PokemonViewModel.filtrarPokemonsPorNombre(newText))
                        pokemonRecyclerView.adapter = adaptador
                    }
                    Log.d("Hola", "Tecla")
                    return true
                }
            })
        }
    }



    companion object {

        @JvmStatic
        fun newInstance() =
            PokeListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }



    /*val pokemonRepository = PokemonRepository { listaProvisional ->
            if (listaProvisional != null && listaProvisional.results.isNotEmpty()) {

                // Si no esta vacia obtenemos cada Pokemon
                for (pokemon in listaProvisional.results) {

                    Log.d("HILOS", "Entra en el for")
                    // De cada pokemon extraemos su URL y la metemos en la funcion GET
                    lifecycleScope.launch {

                        val serviceGetPokemon = PokeAPIClient.pokeAPIService.getPokemonDetails(pokemon.url!!)

                        // Ejecutamos el metodo de manera asincrona
                        serviceGetPokemon.enqueue(object : Callback<Pokemon> {

                            override fun onResponse(
                                call: Call<Pokemon>,
                                response: Response<Pokemon>
                            ) {
                                val pokemon: Pokemon? = response.body()
                                if (pokemon != null) {
                                    PokemonViewModel.listaPokemons.add(pokemon)
                                    Log.d("HILOS", pokemon.toString())
                                    Log.d("HILOS", PokemonViewModel.listaPokemons.toString())
                                }

                            }

                            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                                Log.e("Prueba", "Error en la respuesta: va mal")
                            }
                        })
                    }
                }



            }
            adaptador = PokemonAdapter(PokemonViewModel.listaPokemons)
            val layoutManager = GridLayoutManager(this.context, 1)
            pokemonRecyclerView.layoutManager = layoutManager
            pokemonRecyclerView.adapter = adaptador

        }*/


    /*val pokemonRepositorymalo = PokemonRepository { listaPokemon ->
        Log.d("ORDEN_LISTA", listaPokemon.toString())
        adaptador = PokemonAdapter(listaPokemon)
        // Esto de aqui
        val layoutManager = GridLayoutManager(this.context, 1)
        pokemonRecyclerView.layoutManager = layoutManager
        pokemonRecyclerView.adapter = adaptador


        // Hasta aqui puede dar problemas
        /*adaptador.click = { position, pokemon ->
            run {
                PokemonViewModel.selected = pokemon
                val fm: FragmentManager = parentFragmentManager
                // Aqui haria el cambio a horizontal o vertical
            }

        }*/

        //showFragmentWithPokemonList()

¡
    }*/


}
