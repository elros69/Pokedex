package com.example.pokedex20.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedex20.R




/**
 * A simple [Fragment] subclass.
 * Use the [PokeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokeDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poke_detail, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PokeDetailFragment().apply {

            }
    }
}