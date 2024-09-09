package com.example.a14d_tp1

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.os.SharedMemory
import android.widget.*
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var sp: SharedPreferences
    private lateinit var username_field: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        username_field = findViewById(R.id.username)
        sp = getSharedPreferences("userPrefs", MODE_PRIVATE)
        if(sp.contains("nom")) {
            username_field.setText(sp.getString("nom", ""))
        }
        intent = Intent(this@MainActivity, AccueilActivity::class.java)
        findViewById<Button>(R.id.bt_login).setOnClickListener {
            sp.edit().putString("nom", username_field.text.toString()).apply()
            startActivity(intent)
            Toast.makeText(this, "Connecté!", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.bt_forget).setOnClickListener {
            sp.edit().remove("nom").apply()
            sp.edit().remove("balance").apply()
            username_field.setText("")
        }
    }
}