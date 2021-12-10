package com.ojanbelajar.suitmediatest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ojanbelajar.suitmediatest.databinding.SplashScreenBinding
import org.jetbrains.anko.startActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenActivity : AppCompatActivity(){

    lateinit var binding: SplashScreenBinding
    var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timer.schedule(3000) {
            startActivity<MainActivity>()
            finish()
        }
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }
}