package com.examples.photogalleryapp.ui.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.examples.photogalleryapp.databinding.ActivityMainBinding
import com.examples.photogalleryapp.databinding.ActivitySpalshBinding
import com.examples.photogalleryapp.ui.Home.MainActivity

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySpalshBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpalshBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)


    }
}