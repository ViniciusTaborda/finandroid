package com.finandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {
    private val bankOptions: Array<String> = arrayOf("C6 Bank", "Inter", "Santander", "NuBank", "Banco do Brasil")
    private val categoryOptions: Array<String> = arrayOf("Lazer", "Mercado", "Educação", "Roupas", "Saúde", "Transporte", "Viagens", "Cosméticos", "Casa")

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this@HomeActivity, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this@HomeActivity, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this@HomeActivity, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this@HomeActivity, R.anim.to_bottom_anim) }

    private var includeTransactionButtonClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val valueTransactionInput = findViewById<EditText>(R.id.valueTransactionInput)
        val descriptionTransactionInput = findViewById<EditText>(R.id.descriptionTransactionInput)

        val bankOptionAutoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.accountOptionAutoCompleteTextView)
        val bankOptionsArrayAdapter = ArrayAdapter(this@HomeActivity, android.R.layout.simple_spinner_dropdown_item, bankOptions)
        bankOptionAutoCompleteTextView.setAdapter(bankOptionsArrayAdapter)

        val categoryOptionsAutoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.categoryOptionAutoCompleteTextView)
        val categoryOptionsArrayAdapter = ArrayAdapter(this@HomeActivity, android.R.layout.simple_dropdown_item_1line, categoryOptions)
        categoryOptionsAutoCompleteTextView.setAdapter(categoryOptionsArrayAdapter)

        val includeTransactionButton = findViewById<FloatingActionButton>(R.id.includeTransactionButton)
        val incomeTransactionButton = findViewById<FloatingActionButton>(R.id.incomeTransactionButton)
        val expenseTransactionButton = findViewById<FloatingActionButton>(R.id.expenseTransactionButton)

        includeTransactionButton.setOnClickListener {
            onIncludeTransactionButtonClicked()
        }

        incomeTransactionButton.setOnClickListener {
            Toast.makeText(this, "Income clicked", Toast.LENGTH_SHORT).show()
        }

        expenseTransactionButton.setOnClickListener {
            Toast.makeText(this, "Expense clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = this@HomeActivity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this@HomeActivity!!.currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun onIncludeTransactionButtonClicked() {
        setVisibility(includeTransactionButtonClicked)
        setAnimation(includeTransactionButtonClicked)
        includeTransactionButtonClicked = !includeTransactionButtonClicked
    }

    private fun setVisibility(clicked: Boolean) {
        val incomeTransactionButton = findViewById<FloatingActionButton>(R.id.incomeTransactionButton)
        val expenseTransactionButton = findViewById<FloatingActionButton>(R.id.expenseTransactionButton)

        if (!clicked) {
            incomeTransactionButton.visibility = View.VISIBLE
            expenseTransactionButton.visibility = View.VISIBLE
        } else {
            incomeTransactionButton.visibility = View.INVISIBLE
            expenseTransactionButton.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        val includeTransactionButton = findViewById<FloatingActionButton>(R.id.includeTransactionButton)
        val incomeTransactionButton = findViewById<FloatingActionButton>(R.id.incomeTransactionButton)
        val expenseTransactionButton = findViewById<FloatingActionButton>(R.id.expenseTransactionButton)

        if (!clicked) {
            incomeTransactionButton.startAnimation(fromBottom)
            expenseTransactionButton.startAnimation(fromBottom)
            includeTransactionButton.startAnimation(rotateOpen)
        } else {
            incomeTransactionButton.startAnimation(toBottom)
            expenseTransactionButton.startAnimation(toBottom)
            includeTransactionButton.startAnimation(rotateClose)
        }
    }
}