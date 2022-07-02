package com.example.pesoapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pesoapp.R
import com.example.pesoapp.View.adapter.ViewPagerAdapter
import com.example.pesoapp.databinding.ActivitySliderBinding

class Activity_slider : AppCompatActivity() {
    private lateinit var binding: ActivitySliderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)
        binding = ActivitySliderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //para el view pager
        binding.viewpager.adapter = ViewPagerAdapter(supportFragmentManager)

        //para el idicador
        binding.indicator.setViewPager(binding.viewpager)
    }
}