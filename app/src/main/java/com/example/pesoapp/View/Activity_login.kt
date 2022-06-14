package com.example.pesoapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pesoapp.R
import com.example.pesoapp.databinding.ActivityLoginBinding


class Activity_login : AppCompatActivity() {

    private lateinit var etUsername :EditText
    private lateinit var etPassword :EditText
    private lateinit var btnValidate :Button

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        etUsername = binding.tvUsername
        etPassword = binding.password
        btnValidate = binding.btnIniciar

        btnValidate.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val apassword = etPassword.text.toString().trim()

            if (username.isEmpty()){
                etUsername.error = "El nombre de usuario es requerido"
                return@setOnClickListener
            }
            else if(apassword.isEmpty()){
                etPassword.error ="La contraseña es requerida"
                return@setOnClickListener
            }
            else{
                Toast.makeText(this, "Usuario ha sido creado", Toast.LENGTH_SHORT).show()
            }
        }

    }

    /*fun onClickArteLista(view: View) {

        val intent = Intent(this, activity_menu::class.java)
        startActivity(intent)ZX
    }*/

    fun onClickCrearCuenta(view: View) {
        val intent = Intent(this, Activity_crearcuenta::class.java)
        startActivity(intent)
    }
}