package com.example.pokedex20.activities

import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.pokedex20.R
import com.example.pokedex20.data.PokemonViewModel
import com.example.pokedex20.database.UserDataBase
import com.example.pokedex20.database.Usuario
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch
import android.Manifest
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.documentfile.provider.DocumentFile
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.io.InputStream
import kotlin.math.log

class PrincipalActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolBar: Toolbar
    private lateinit var userLoggedTextField: TextView
    private lateinit var imageButtonNav: Button
    private lateinit var imageView: ImageView

    private lateinit var viewModel: PokemonViewModel

    private lateinit var dataBase: UserDataBase

    /*companion object {
        private const val REQUEST_CODE_PERMISSION = 123
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#290E0E")
        }

        dataBase = UserDataBase.getInstance(this)

        Log.d("VAMOS A VER", PokemonViewModel.getLoggedInUser()?.nombre ?: "No existe")

        drawerLayout = findViewById(R.id.principalDrawerLayout)
        navigationView = findViewById(R.id.navigationView)
        toolBar = findViewById(R.id.principalToolBar)

        val headerView = navigationView.getHeaderView(0)

        userLoggedTextField = headerView.findViewById(R.id.userLoggedTextField)
        imageButtonNav = headerView.findViewById(R.id.imageButonNav)
        imageView = headerView.findViewById(R.id.imageView)

        userLoggedTextField.text = PokemonViewModel.getLoggedInUser()?.nombre

        Glide.with(this)
            .asGif()
            .load(R.raw.pokeball_spin_gif)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)

        // Configuramos el toolbar
        setSupportActionBar(toolBar)


        // Configurar el action var
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        // Añadir el action bar al layout
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        /*val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permiso concedido, puedes realizar la operación que requiere el permiso aquí
                lifecycleScope.launch {
                    if (PokemonViewModel.getLoggedInUser()?.imageUri != "") {
                        imageView.setImageURI(Uri.parse(PokemonViewModel.getLoggedInUser()!!.imageUri))
                    } else {
                        imageView.setImageResource(R.drawable.pokebal_user_icon)
                    }
                }
            } else {
                // El usuario denegó el permiso, puedes mostrar un mensaje indicando que la operación no se puede realizar.
            }
        }

        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            lifecycleScope.launch {
                if (PokemonViewModel.getLoggedInUser()?.imageUri != "") {
                    imageView.setImageURI(Uri.parse(PokemonViewModel.getLoggedInUser()!!.imageUri))
                } else {
                    imageView.setImageResource(R.drawable.pokebal_user_icon)
                }
            }
        } else {
            requestPermissionLauncher.launch(permission)
        }*/

        /*val permission = Manifest.permission.READ_EXTERNAL_STORAGE

        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            Log.d("PERMISION", "EL PERMISO ESta concedido")
            // El permiso ya está concedido, puedes acceder a la URI aquí
            lifecycleScope.launch {
                if (PokemonViewModel.getLoggedInUser()?.imageUri != "") {
                    Log.d("PERMISION", PokemonViewModel.getLoggedInUser()!!.imageUri)
                    val imageUri = Uri.parse(PokemonViewModel.getLoggedInUser()!!.imageUri)
                    val documentFile = DocumentFile.fromSingleUri(this@PrincipalActivity, imageUri)
                    Log.d("PERMISION_DOCUMENT",documentFile.toString())

                    if (documentFile != null && documentFile.exists()) { // && documentFile.exists()
                        // El archivo existe, puedes manejarlo aquí
                        // Por ejemplo, establecer la imagen en un ImageView
                        imageView.setImageURI(imageUri)
                    } else {
                        // El archivo no existe o no es accesible
                        // Aquí puedes manejar la situación de acuerdo a tus necesidades
                        Log.d("PERMISION_DOCUMENTNO", documentFile.toString())
                        imageView.setImageResource(R.drawable.pokebal_user_icon)
                    }
                } else {
                    imageView.setImageResource(R.drawable.pokebal_user_icon)
                }
            }
        } else {
            // El permiso no está concedido, solicitarlo al usuario
            ActivityCompat.requestPermissions(this, arrayOf(permission), REQUEST_CODE_PERMISSION)
        }*/



//var uriString: String = ""

        /*var drawableImage: Drawable
        var inputStream: InputStream

        imageButtonNav.setOnClickListener {
            getImage.launch("image/*")
            Log.d("ACTUALIZAR", "Ha entrao AL BOTON")
        }*/*/

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer((GravityCompat.START))
            }
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /*override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    lifecycleScope.launch {
                        if (PokemonViewModel.getLoggedInUser()?.imageUri != "") {
                            imageView.setImageURI(Uri.parse(PokemonViewModel.getLoggedInUser()!!.imageUri))
                        } else {
                            imageView.setImageResource(R.drawable.pokebal_user_icon)
                        }
                    }
                } else {
                    // Permiso denegado, puedes mostrar un mensaje al usuario indicando que la operación no se puede realizar.
                }
            }
        }
    }*/
}