package com.finandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    private val bankOptions: Array<String> = arrayOf("C6 Bank", "Inter", "Santander", "NuBank", "Banco do Brasil")
    private val categoryOptions: Array<String> = arrayOf("Lazer", "Mercado", "Educação", "Roupas", "Saúde", "Transporte", "Viagens", "Cosméticos", "Casa")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bankOptionsSpinner = findViewById<Spinner>(R.id.bankOptionSpinner)
        val bankOptionsArrayAdapter = ArrayAdapter(this@HomeActivity, android.R.layout.simple_spinner_dropdown_item, bankOptions)

        bankOptionsSpinner.adapter = bankOptionsArrayAdapter
        bankOptionsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(applicationContext, "Selected is " + bankOptions[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val categoryOptionsSpinner = findViewById<Spinner>(R.id.categoryOptionSpinner)
        val categoryOptionsArrayAdapter = ArrayAdapter(this@HomeActivity, android.R.layout.simple_spinner_dropdown_item, categoryOptions)

        categoryOptionsSpinner.adapter = categoryOptionsArrayAdapter
        categoryOptionsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(applicationContext, "Selected is " + categoryOptions[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}