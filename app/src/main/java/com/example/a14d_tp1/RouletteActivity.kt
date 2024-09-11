package com.example.a14d_tp1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RouletteActivity : AppCompatActivity() {
    lateinit var sp: android.content.SharedPreferences
    lateinit var btn_lancer: android.widget.Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_roulette)


    }
}