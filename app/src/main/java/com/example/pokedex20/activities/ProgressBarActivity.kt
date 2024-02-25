package com.example.pokedex20.activities

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.ImageViewTarget
import com.example.pokedex20.R
import com.example.pokedex20.data.PokemonViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URL

class ProgressBarActivity : AppCompatActivity() {

    lateinit var gifImageView: ImageView
    lateinit var progressBar: ProgressBar
    lateinit var viewModel: PokemonViewModel


    /*private val handler = Handler(Looper.getMainLooper())
    private val tiempoMinimoCarga = 5000L
    private var isListLoaded = false*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#290E0E")
        }

        gifImageView = findViewById(R.id.pokeballSpinGif)
        progressBar = findViewById(R.id.progressBarPokemon)

        // Inicializo el viewModel
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        // Temporizador de la barra

        /*handler.postDelayed( {
            if (!isListLoaded) {
                progressBar.visibility = View.VISIBLE
            } else { }
        }, tiempoMinimoCarga)*/


        Glide.with(this)
            .asGif()
            .load(R.raw.pokeball_spin_gif)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(gifImageView)
        // Observamos los cambios en la lista

        /*lifecycleScope.launch {
            repeat(10) {
                delay(500)
                updateProgressBar((it + 1) * 10)

                if (it == 9) {
                    startNewActivity()
                }
            }
        }*/

        viewModel.pokemonListLiveData.observe(this, Observer { pokemonList ->

            // Cargar barra
            lifecycleScope.launch {
                repeat(10) {
                    delay(500)
                    updateProgressBar((it + 1) * 10)

                    if (it == 9 && pokemonList != null) {
                        startNewActivity()
                    }
                }
            }
            PokemonViewModel.ordenarPokemonsPorIdDeMenorAMayor()
            //isListLoaded = true

            // Cargar GIF
            //Log.d("LISTA_PROGRESS", pokemonList.toString())

        })



    }

    private fun updateProgressBar(progress: Int) {
        // Asegúrate de estar en el hilo principal para actualizar la interfaz de usuario
        Handler(Looper.getMainLooper()).post {
            progressBar.progress = progress
        }
    }

    private fun startNewActivity() {
        // Aquí deberías iniciar tu nueva actividad
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        // También puedes finalizar esta actividad si ya no la necesitas
        finish()
    }


}