package com.example.pesoapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.pesoapp.R
import com.example.pesoapp.View.adapter.ViewPagerAdapter
import com.example.pesoapp.databinding.ActivitySliderBinding

class Activity_slider : AppCompatActivity() {
    private lateinit var binding: ActivitySliderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySliderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSkip.setOnClickListener {
            startActivity(Intent(this,Activity_login::class.java))
        }

        binding.viewpager.adapter=ViewPagerAdapter(supportFragmentManager)

        binding.indicator.setViewPager(binding.viewpager)

    }
}