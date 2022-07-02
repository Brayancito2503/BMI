package com.example.pesoapp.View


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.pesoapp.R
import com.example.pesoapp.View.adapter.ViewPagerAdapter
import com.example.pesoapp.databinding.ActivitySliderBinding
import me.relex.circleindicator.CircleIndicator


class Activity_slider : AppCompatActivity() {

    private lateinit var binding: ActivitySliderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySliderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val viewpager = view.findViewById<View>(R.id.viewpager) as ViewPager
//        viewpager.adapter = adapter

//        val indicator = view.findViewById<View>(R.id.indicator) as CircleIndicator
//        indicator.setViewPager(viewpager)

         binding.indicator.adapterDataObserver

//        adapter.registerDataSetObserver(indicator.dataSetObserver)

        binding.viewpager.adapter = ViewPagerAdapter(supportFragmentManager)

    }
}