package com.example.pesoapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pesoapp.R
import com.example.pesoapp.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Activity_login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        firebaseAuth= FirebaseAuth.getInstance()
        checkuser()
        binding.btnIniciar.setOnClickListener(::onEntrarBtnClicked)



    }

    private fun checkuser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            startActivity(Intent(this@Activity_login,Activity_menu::class.java))
            finish()
        }
    }
    private fun onEntrarBtnClicked(view: View) {

        val mEmail=binding.tvUsername.text.toString()
        val mPassword=binding.password.text.toString()

        when{
            mEmail.isEmpty()|| mPassword.isEmpty()->
            {

                if(mEmail.isEmpty())
                {
                    binding.tvUsername.setError("Ingrese el campo requerido")
                }
                if (mPassword.isEmpty())
                {
                    binding.password.setError("Ingrese el campo requerido")
                }
                Toast.makeText(baseContext, "Correo o Contraseña incorrectos o incompletos",
                    Toast.LENGTH_SHORT).show()
            }
            else->
            {
                SignIn(mEmail,mPassword)
            }
        }
    }

    private fun SignIn(email:String,password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithEmail:success")
                    Toast.makeText(baseContext, "Bienvenido",
                        Toast.LENGTH_SHORT).show()
                    reload()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }//Fin Funcion SignIn

    private fun reload(){
        val intent = Intent(this,Activity_menu::class.java)
        this.startActivity(intent)
    }//Fin de la Funcion reload


}