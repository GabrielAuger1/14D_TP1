package com.example.a14d_tp1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

class RouletteActivity : AppCompatActivity() {
    lateinit var sp: android.content.SharedPreferences
    lateinit var btn_lancer: Button
    lateinit var rd_pair: RadioButton
    lateinit var rd_impair: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_roulette)
        sp = getSharedPreferences("userPrefs", MODE_PRIVATE)
        btn_lancer = findViewById(R.id.bt_lancer)
        rd_pair = findViewById(R.id.rd_pair)
        rd_impair = findViewById(R.id.rd_impair)
    }
}