package com.example.pesoapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.pesoapp.R
import com.example.pesoapp.databinding.ActivitySplashscreenBinding

class Activity_splashscreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val animlogo = AnimationUtils.loadAnimation(this, R.anim.animacion)
        val imglogo: ImageView = binding.imglogo
        imglogo.startAnimation(animlogo)

        val handler = Handler()
        handler.postDelayed({ // Do something after 5s = 5000ms
            val intent = Intent(this,Activity_login::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}
