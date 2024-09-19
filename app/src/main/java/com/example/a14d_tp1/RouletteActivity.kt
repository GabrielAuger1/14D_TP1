package com.example.a14d_tp1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

@Suppress("SpellCheckingInspection")
class RouletteActivity : AppCompatActivity() {
    private lateinit var sp: android.content.SharedPreferences
    private lateinit var btnLancer: Button
    private lateinit var rdPair: RadioButton
    private lateinit var rdImpair: RadioButton
    private lateinit var resultat: Toast
    private lateinit var bet: EditText
    private lateinit var txtboxCredit: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_roulette)
        sp = getSharedPreferences("userPrefs", MODE_PRIVATE)
        btnLancer = findViewById(R.id.bt_lancer)
        rdPair = findViewById(R.id.rd_pair)
        rdImpair = findViewById(R.id.rd_impair)
        bet = findViewById(R.id.bet)
        txtboxCredit = findViewById(R.id.credit)
        txtboxCredit.text = sp.getInt("balance", 0).toString()
        checkboxManager(rdPair)
        checkboxManager(rdImpair)
        btnLancer.setOnClickListener {
            lancer()
        }
    }
    private fun checkboxManager(view: android.view.View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when (view.id) {
                R.id.rd_pair -> {
                    if (checked) {
                        rdImpair.isChecked = false
                    }
                }
                R.id.rd_impair -> {
                    if (checked) {
                        rdPair.isChecked = false
                    }
                }
            }
        }
    }
    private fun lancer() {
        val random = (0..36).random()
        val pair = random % 2 == 0
        val impair = random % 2 != 0
        val pairChecked = rdPair.isChecked
        val impairChecked = rdImpair.isChecked
        val editor = sp.edit()
        val balance = sp.getInt("balance", 0)
        if (bet.text.toString().toInt() > balance) {
            resultat = Toast.makeText(this, "Vous n'avez pas assez d'argent", Toast.LENGTH_SHORT)
            resultat.show()
            return
        }
        else if (bet.text.toString().toInt() <= 0) {
            resultat = Toast.makeText(this, "Vous devez miser une somme positive", Toast.LENGTH_SHORT)
            resultat.show()
            return
        }


        editor.putInt("balance", balance - bet.text.toString().toInt())
        txtboxCredit.text = sp.getInt("balance", 0).toString()
        editor.putInt("random", random)
        editor.putBoolean("pair", pair)
        editor.putBoolean("impair", impair)
        editor.putBoolean("pair_checked", pairChecked)
        editor.putBoolean("impair_checked", impairChecked)
        editor.apply()
        resultManager(pairChecked, pair, impairChecked, impair)
    }

    private fun resultManager(
        pairChecked: Boolean,
        pair: Boolean,
        impairChecked: Boolean,
        impair: Boolean
    ) {
        if (pairChecked && pair) {
            sp.edit().putInt("balance", sp.getInt("balance", 0) + bet.text.toString().toInt() * 2).apply()
            txtboxCredit.text = sp.getInt("balance", 0).toString()
        } else if (impairChecked && impair) {
            sp.edit().putInt("balance", sp.getInt("balance", 0) + bet.text.toString().toInt() * 2).apply()
            txtboxCredit.text = sp.getInt("balance", 0).toString()
        } else {
            txtboxCredit.text = sp.getInt("balance", 0).toString()
        }
    }
}