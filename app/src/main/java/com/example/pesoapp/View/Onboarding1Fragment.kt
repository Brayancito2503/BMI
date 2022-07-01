package com.example.pesoapp.View

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import com.example.pesoapp.R
import com.example.pesoapp.databinding.ActivitySliderBinding
import com.example.pesoapp.databinding.FragmentCalculadoraBinding
import com.example.pesoapp.databinding.FragmentOnboarding1Binding

class Onboarding1Fragment : Fragment() {
    private var fbinding: FragmentOnboarding1Binding? = null
    private val binding get() = fbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun likeAnimation(imageView: LottieAnimationView,
                              animation: Int,
                              like: Boolean) : Boolean {

        if (!like) {
            imageView.setAnimation(animation)
            imageView.playAnimation()
            imageView.repeatCount=3
        }
        return !like
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        fbinding = FragmentOnboarding1Binding.inflate(layoutInflater)
        val view = binding.root
        var like = false

        likeAnimation(binding.imglogo, R.raw.bmi, like)

        return view
    }



}