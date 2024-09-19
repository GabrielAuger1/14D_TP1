package com.example.a14d_tp1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AccueilActivity : AppCompatActivity() {

    private lateinit var sp: SharedPreferences
    private lateinit var username: android.widget.TextView
    private lateinit var balance: android.widget.TextView
    private lateinit var btnJeux: android.widget.Button
    private lateinit var btnBanque: android.widget.Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_accueil)

        sp = getSharedPreferences("userPrefs", MODE_PRIVATE)
        username = findViewById(R.id.user)
        balance = findViewById(R.id.balance)
        btnJeux = findViewById(R.id.bt_jeux)
        btnBanque = findViewById(R.id.bt_banque)

        username.text = sp.getString("nom", "Inconnu")
        balance.text = sp.getInt("balance", 0).toString()

        btnJeux.setOnClickListener {
            intent = Intent(this@AccueilActivity, RouletteActivity::class.java)
            startActivity(intent)
        }
        btnBanque.setOnClickListener {
            intent = Intent(this@AccueilActivity, GuichetDeBanqueActivity::class.java)
            startActivity(intent)
        }
    }
}