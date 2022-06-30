package com.example.pesoapp.View

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.airbnb.lottie.LottieAnimationView
import com.example.pesoapp.R
import com.example.pesoapp.databinding.ActivityMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Activity_menu : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bell = false

//        val menubmi: BottomNavigationView = binding.bottomNavMenu
//        setupWithNavController(
//            menubmi,
//            Navigation.findNavController(this, R.id.frag_navgraph)
//        )

        val navHost = supportFragmentManager.findFragmentById(androidx.navigation.fragment.R.id.nav_host_fragment_container)!!
        val navController = navHost.findNavController()

        binding.bottomNavMenu.setupWithNavController(navController)



    }

}