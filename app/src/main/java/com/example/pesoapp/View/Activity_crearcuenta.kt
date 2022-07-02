package com.example.pesoapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.pesoapp.R
import com.example.pesoapp.databinding.ActivityCrearcuentaBinding
import com.google.firebase.auth.FirebaseAuth

class Activity_crearcuenta : AppCompatActivity() {

    private lateinit var binding: ActivityCrearcuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //HOLA
        super.onCreate(savedInstanceState)
        binding = ActivityCrearcuentaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//--------------------------

        binding.btnCrearCuenta.setOnClickListener {
            if (valida().equals(true)) {
                addCuentaUsuario()
                finish()
            }
        }
    }

    fun addCuentaUsuario() {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.createUserWithEmailAndPassword(
            binding.tvEmailCuenta.text.toString(),
            binding.tvPasswordCuenta.text.toString()
        )
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "El usuario ha sido creado", Toast.LENGTH_SHORT).show();
                    finish()
                } else {
                    Toast.makeText(this, "El usuario no ha sido creado", Toast.LENGTH_SHORT).show();
                }
            }
    }

    //Validar que los campos no sean nulos
    fun valida(): Boolean {
        try {
            var validaok: Boolean = false
//-- El email es un valor requerido
            if (binding.tvEmailCuenta.length()?.equals(0)!!) {
                binding.tvEmailCuenta.requestFocus()
                binding.tvEmailCuenta.setError("Email es un valor requerido")
                return validaok
            }
//-- La contraseña es un valor requerido
            if
                    (binding.tvPasswordCuenta.text?.length?.equals(0)!!) {
                binding.tvPasswordCuenta.requestFocus()
                binding.tvPasswordCuenta.setError("Debe ingresar una contraseña")
                return validaok
            }
//La confirmación de contraseña es un valor requerido
            if
                    (binding.tvConfirmarContrasenia.text?.length?.equals(0)!!) {
                binding.tvConfirmarContrasenia.requestFocus()
                binding.tvConfirmarContrasenia.setError("Debe ingresar la confirmación de contraseña")
                return validaok
            }
//La contraseña debe ser igual a la confirmación de la contraseña
            val strpassword: String = if
                                              (binding.tvConfirmarContrasenia.text != null)
                binding.tvPasswordCuenta.text.toString() else ""
            val strpasswordconfirmar: String = if (binding.tvConfirmarContrasenia.text != null)
                binding.tvConfirmarContrasenia.text.toString() else ""
            if (strpassword.equals(strpasswordconfirmar) == false) {
                binding.tvPasswordCuenta.requestFocus()
                binding.tvPasswordCuenta.setError("El Password y la confirmación deben coincidir")
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