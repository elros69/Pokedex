package com.example.pokedex20.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.ActivityChooserView.InnerLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.pokedex20.R
import com.example.pokedex20.data.PokemonViewModel
import com.example.pokedex20.database.UserDataBase
import com.example.pokedex20.database.Usuario
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var registerButton: Button

    lateinit var addUserName: EditText
    lateinit var addUserPassword: EditText

    lateinit var dataBase: UserDataBase

    lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#290E0E")
        }

        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        dataBase = UserDataBase.getInstance(this)

        lifecycleScope.launch {
            val usuario: Usuario = Usuario(
                "abraham",
                "1234",
            )
            dataBase.usuarioDAO().insertarUsuario(usuario)
        }

        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)
        addUserName = findViewById(R.id.addUserName)
        addUserPassword = findViewById(R.id.addUserPassword)

        loginButton.setOnClickListener {
            val nombre = addUserName.text.toString()
            val contraseña = addUserPassword.text.toString()

            lifecycleScope.launch {
                val usuario = dataBase.usuarioDAO().obtenerUsuario(nombre)

                if (usuario != null && usuario.contraseña == contraseña) {
                    //viewModel.setLoggedInUser(usuario)
                    PokemonViewModel.setLoggedInUser(usuario)
                    val intent = Intent(this@LoginActivity, PrincipalActivity::class.java)
                    Log.d("VAMOS A VER EN EL LOGIN", PokemonViewModel.loggedInUserInternal?.nombre ?: "No existe")
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Usuario no existe, o contraseña incorrecta", Toast.LENGTH_LONG).show()
                }
            }
        }

        registerButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}