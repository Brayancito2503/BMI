package com.example.pesoapp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import com.example.pesoapp.R
import com.example.pesoapp.databinding.FragmentOnboarding1Binding
import com.example.pesoapp.databinding.FragmentOnboarding2Binding


class Onboarding2Fragment : Fragment() {
    private var fbinding: FragmentOnboarding2Binding? = null
    private val binding get() = fbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
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
        fbinding = FragmentOnboarding2Binding.inflate(layoutInflater)
        val view = binding.root
        var like = false

        likeAnimation(binding.imglogo, R.raw.scale, like)

        return view
    }
}