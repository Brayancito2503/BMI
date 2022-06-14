package com.example.pesoapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.pesoapp.R
import com.example.pesoapp.databinding.ActivityMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Activity_menu : AppCompatActivity() {

    lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menubmi: BottomNavigationView = binding.bottomNavMenu
        NavigationUI.setupWithNavController(
            menubmi,
            Navigation.findNavController(this, R.id.frag_navgraph)
        )
    }
}