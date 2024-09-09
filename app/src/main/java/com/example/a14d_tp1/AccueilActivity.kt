package com.example.a14d_tp1

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AccueilActivity : AppCompatActivity() {

    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_accueil)
        sp = getSharedPreferences("userPrefs", MODE_PRIVATE)

        findViewById<android.widget.TextView>(R.id.user).text = sp.getString("nom", "Utilisateur")
        findViewById<android.widget.TextView>(R.id.balance).text = sp.getInt("balance", 0).toString()

        findViewById<android.widget.Button>(R.id.bt_jeux).setOnClickListener {
            intent = android.content.Intent(this@AccueilActivity, RouletteActivity::class.java)
            startActivity(intent)
        }
        findViewById<android.widget.Button>(R.id.bt_banque).setOnClickListener {
            intent = android.content.Intent(this@AccueilActivity, GuichetDeBanqueActivity::class.java)
            startActivity(intent)
        }
    }
}