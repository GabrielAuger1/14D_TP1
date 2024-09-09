package com.example.a14d_tp1

import android.os.Bundle
import android.widget.*
import android.window.OnBackInvokedDispatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GuichetDeBanqueActivity : AppCompatActivity() {
    private lateinit var sp: android.content.SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_guichet_de_banque)
        sp = getSharedPreferences("userPrefs", MODE_PRIVATE)
        findViewById<TextView>(R.id.balance).text = sp.getInt("balance", 0).toString()
        findViewById<TextView>(R.id.user).text = sp.getString("nom", "Utilisateur")
        findViewById<Button>(R.id.bt_deposer).setOnClickListener {
            val montant = findViewById<EditText>(R.id.montant).text.toString().toInt()
            sp.edit().putInt("balance", sp.getInt("balance", 0) + montant).apply()
            findViewById<TextView>(R.id.balance).text = sp.getInt("balance", 0).toString()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}