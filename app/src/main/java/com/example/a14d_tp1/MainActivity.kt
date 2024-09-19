package com.example.a14d_tp1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var sp: SharedPreferences
    private lateinit var usernameField: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usernameField = findViewById(R.id.username)
        sp = getSharedPreferences("userPrefs", MODE_PRIVATE)
        if(sp.contains("nom")) {
            usernameField.setText(sp.getString("nom", ""))
        }
        intent = Intent(this@MainActivity, AccueilActivity::class.java)
        findViewById<Button>(R.id.bt_login).setOnClickListener {
            sp.edit().putString("nom", usernameField.text.toString()).apply()
            startActivity(intent)
            Toast.makeText(this, "Connecté!", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.bt_forget).setOnClickListener {
            sp.edit().remove("nom").apply()
            sp.edit().remove("balance").apply()
            usernameField.setText("")
        }
    }
}