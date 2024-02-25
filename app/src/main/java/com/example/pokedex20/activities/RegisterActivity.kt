package com.example.pokedex20.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.pokedex20.R
import com.example.pokedex20.database.UserDataBase
import com.example.pokedex20.database.Usuario
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    lateinit var dataBase: UserDataBase

    lateinit var addUserNameRegister: EditText
    lateinit var addUserPasswordRegister: EditText
    lateinit var registerUserButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        dataBase = UserDataBase.getInstance(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#290E0E")
        }

        addUserNameRegister = findViewById(R.id.addUserNameRegister)
        addUserPasswordRegister = findViewById(R.id.addUserPsasswordRegister)
        registerUserButton = findViewById(R.id.registerUserButton)

        registerUserButton.setOnClickListener {
            val nombre = addUserNameRegister.text.toString()
            val contraseña = addUserPasswordRegister.text.toString()

            lifecycleScope.launch {
                if (dataBase.usuarioDAO().obtenerUsuario(nombre) == null) {
                    val nuevoUsuario = Usuario(
                        nombre,
                        contraseña,
                        )
                    dataBase.usuarioDAO().insertarUsuario(nuevoUsuario)

                    Toast.makeText(this@RegisterActivity, "Usuario Insertado", Toast.LENGTH_LONG).show()

                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@RegisterActivity, "El usuario ya existe", Toast.LENGTH_LONG).show()
                }
            }

        }

    }
}