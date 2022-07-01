package com.example.pesoapp.View

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.example.pesoapp.R
import com.example.pesoapp.databinding.ActivitySplashscreenBinding

class Activity_splashscreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//
//        val animlogo = AnimationUtils.loadAnimation(this, R.anim.animacion)
//        val imglogo: ImageView = binding.imglogo
//        imglogo.startAnimation(animlogo)

        val handler = Handler()
        handler.postDelayed({ // Do something after 5s = 5000ms
            val intent = Intent(this,Activity_login::class.java)
            startActivity(intent)
            finish()
        }, 5000)

        var like = false

        likeAnimation(binding.imglogo, R.raw.pesas2, like)


    }

    private fun likeAnimation(imageView: LottieAnimationView,
                              animation: Int,
                              like: Boolean) : Boolean {

        if (!like) {
            imageView.setAnimation(animation)
            imageView.playAnimation()
        } else {
            imageView.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animator: Animator) {

                        imageView.setImageResource(R.drawable.bmilogo)
                        imageView.alpha = 1f
                    }

                })

        }

        return !like
    }
}
