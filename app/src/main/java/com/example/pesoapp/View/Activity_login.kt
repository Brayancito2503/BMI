package com.example.pesoapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pesoapp.R
import com.example.pesoapp.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class Activity_login : AppCompatActivity() {



    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        //----------------------
        val view = binding.root
        setContentView(view)
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        binding.btnIniciar.setOnClickListener { v ->
            if (validarUsuario().equals(true)) {
                firebaseAuth.signInWithEmailAndPassword(
                    binding.tvUsername.text.toString(),
                    binding.password.text.toString()
                )
                    .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, Activity_menu::class.java))
                            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                            binding.tvUsername.setText("")
                            binding.password.setText("")
                        } else {
                            Toast.makeText(
                                this,
                                "El usuario y clave no existen",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }
        }
    }
    fun onClickMusicLista(view:View?){
        val intent = Intent(this, Activity_menu::class.java)
        startActivity(intent)
    }
    fun onClickCrearCuenta(view: View?) {
        val intent = Intent(this,
            Activity_crearcuenta::class.java)
        startActivity(intent)
    }
    //Validar que los campos no sean nulos
    fun validarUsuario(): Boolean {
        try {
            var validaok = false
            //-- El email es un valor requerido
            if (binding.tvUsername.text?.length?.equals(0)!!)
            {
                binding.tvUsername.requestFocus()
                binding.tvUsername.setError("Debe ingresar su correo electrónico")
                return validaok
            }
            //-- La contraseña es un valor requerido
            if (binding.password.text?.length?.equals(0)!!) {
                binding.password.requestFocus()
                binding.password.setError("Debe ingresar una contraseña")
                return validaok
            }
            validaok = true
            return validaok
        } catch (e: Exception) {
            e.message?.let { Log.e("Error en valida", it) };
            return false;
        }
    }
}