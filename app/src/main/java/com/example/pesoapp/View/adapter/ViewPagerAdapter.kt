package com.example.pesoapp.View.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.pesoapp.View.Onboarding1Fragment
import com.example.pesoapp.View.Onboarding2Fragment
import com.example.pesoapp.View.Onboarding3Fragment

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragment = listOf(
        Onboarding1Fragment(),
        Onboarding2Fragment(),
        Onboarding3Fragment()
    )

    override fun getCount(): Int {
        return fragment.size
    }

    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

}