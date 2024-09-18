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
    lateinit var resultat: Toast
    lateinit var bet: EditText
    lateinit var txtBox_credit: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_roulette)
        sp = getSharedPreferences("userPrefs", MODE_PRIVATE)
        btn_lancer = findViewById(R.id.bt_lancer)
        rd_pair = findViewById(R.id.rd_pair)
        rd_impair = findViewById(R.id.rd_impair)
        bet = findViewById(R.id.bet)
        txtBox_credit = findViewById(R.id.credit)
        txtBox_credit.text = sp.getInt("balance", 0).toString()
        checkbox_manager(rd_pair)
        checkbox_manager(rd_impair)
        btn_lancer.setOnClickListener {
            lancer(it)
        }
    }
    fun checkbox_manager(view: android.view.View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when (view.id) {
                R.id.rd_pair -> {
                    if (checked) {
                        rd_impair.isChecked = false
                    }
                }
                R.id.rd_impair -> {
                    if (checked) {
                        rd_pair.isChecked = false
                    }
                }
            }
        }
    }
    fun lancer(view: android.view.View) {
        val random = (0..36).random()
        val pair = random % 2 == 0
        val impair = random % 2 != 0
        val pair_checked = rd_pair.isChecked
        val impair_checked = rd_impair.isChecked
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
        txtBox_credit.text = sp.getInt("balance", 0).toString()
        editor.putInt("random", random)
        editor.putBoolean("pair", pair)
        editor.putBoolean("impair", impair)
        editor.putBoolean("pair_checked", pair_checked)
        editor.putBoolean("impair_checked", impair_checked)
        editor.apply()
        resultManager(pair_checked, pair, impair_checked, impair)
    }

    private fun resultManager(
        pair_checked: Boolean,
        pair: Boolean,
        impair_checked: Boolean,
        impair: Boolean
    ) {
        if (pair_checked && pair) {
            sp.edit().putInt("balance", sp.getInt("balance", 0) + bet.text.toString().toInt() * 2).apply()
            txtBox_credit.text = sp.getInt("balance", 0).toString()
        } else if (impair_checked && impair) {
            sp.edit().putInt("balance", sp.getInt("balance", 0) + bet.text.toString().toInt() * 2).apply()
            resultat.show()
            txtBox_credit.text = sp.getInt("balance", 0).toString()
        } else {
            txtBox_credit.text = sp.getInt("balance", 0).toString()
        }
    }
}