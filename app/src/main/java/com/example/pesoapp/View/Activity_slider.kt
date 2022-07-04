package com.example.pesoapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.pesoapp.R
import com.example.pesoapp.View.adapter.ViewPagerAdapter
import com.example.pesoapp.databinding.ActivityLoginBinding
import com.example.pesoapp.databinding.ActivitySliderBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Activity_slider : AppCompatActivity() {
    private lateinit var binding: ActivitySliderBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySliderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSkip.setOnClickListener {
            startActivity(Intent(this,Activity_login::class.java))
        }

       binding.viewpager.adapter=ViewPagerAdapter(supportFragmentManager)

        binding.indicator.setViewPager(binding.viewpager)


        auth = Firebase.auth
        firebaseAuth= FirebaseAuth.getInstance()

        checkuser()


    }
    private fun checkuser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            startActivity(Intent(this@Activity_slider,Activity_menu::class.java))
            finish()
        }
    }
}