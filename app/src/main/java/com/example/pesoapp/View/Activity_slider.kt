package com.example.pesoapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pesoapp.R
import com.example.pesoapp.View.adapter.ViewPagerAdapter
import com.example.pesoapp.databinding.ActivitySliderBinding

class Activity_slider : AppCompatActivity() {
    lateinit var binding: ActivitySliderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)


    }
}