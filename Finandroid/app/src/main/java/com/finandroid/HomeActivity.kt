package com.finandroid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.finandroid.database.TransactionDbHelper
import com.finandroid.models.Transaction
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {
    private val transactionDbHelper = TransactionDbHelper(this)

    private val bankOptions: Array<String> =
        arrayOf("C6 Bank", "Inter", "Santander", "NuBank", "Banco do Brasil")
    private val categoryOptions: Array<String> = arrayOf(
        "Lazer",
        "Mercado",
        "Educação",
        "Roupas",
        "Saúde",
        "Transporte",
        "Viagens",
        "Cosméticos",
        "Casa"
    )

    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            this@HomeActivity,
            R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            this@HomeActivity,
            R.anim.rotate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this@HomeActivity,
            R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this@HomeActivity,
            R.anim.to_bottom_anim
        )
    }

    private var includeTransactionButtonClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val valueTransactionInput = findViewById<EditText>(R.id.valueTransactionInput)
        val descriptionTransactionInput = findViewById<EditText>(R.id.descriptionTransactionInput)

        val bankOptionAutoCompleteTextView =
            findViewById<AutoCompleteTextView>(R.id.accountOptionAutoCompleteTextView)
        val bankOptionsArrayAdapter = ArrayAdapter(
            this@HomeActivity,
            android.R.layout.simple_spinner_dropdown_item,
            bankOptions
        )
        bankOptionAutoCompleteTextView.setAdapter(bankOptionsArrayAdapter)

        val categoryOptionsAutoCompleteTextView =
            findViewById<AutoCompleteTextView>(R.id.categoryOptionAutoCompleteTextView)
        val categoryOptionsArrayAdapter = ArrayAdapter(
            this@HomeActivity,
            android.R.layout.simple_dropdown_item_1line,
            categoryOptions
        )
        categoryOptionsAutoCompleteTextView.setAdapter(categoryOptionsArrayAdapter)

        val includeTransactionButton =
            findViewById<FloatingActionButton>(R.id.includeTransactionButton)
        val incomeTransactionButton =
            findViewById<FloatingActionButton>(R.id.incomeTransactionButton)
        val expenseTransactionButton =
            findViewById<FloatingActionButton>(R.id.expenseTransactionButton)
        val transactionListButton =
            findViewById<FloatingActionButton>(R.id.listTransactionsButton)
        val dashboardButton =
            findViewById<FloatingActionButton>(R.id.dashboardButton)

        transactionListButton.setOnClickListener {
            val transactionIntent = Intent(this, ListTransaction::class.java)
            startActivity(transactionIntent)
        }

        dashboardButton.setOnClickListener {
            val dashboardIntent = Intent(this, Dashboard::class.java)
            startActivity(dashboardIntent)
        }

        fun isFieldsFilled(): Boolean {
            val value = valueTransactionInput.text.toString()
            val bank = bankOptionAutoCompleteTextView.text.toString()
            val category = categoryOptionsAutoCompleteTextView.text.toString()
            val description = descriptionTransactionInput.text.toString()

            if (
                TextUtils.isEmpty(value)
                || TextUtils.isEmpty(bank)
                || TextUtils.isEmpty(category)
                || TextUtils.isEmpty(description)
            ) {
                return false
            }
            return true
        }

        fun cleanFields() {
            valueTransactionInput.setText("")
            descriptionTransactionInput.setText("")
        }

        includeTransactionButton.setOnClickListener {
            onIncludeTransactionButtonClicked()
        }

        incomeTransactionButton.setOnClickListener {
            if (!isFieldsFilled()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val value = valueTransactionInput.text.toString().toDouble()
                val bank = bankOptionAutoCompleteTextView.text.toString()
                val category = categoryOptionsAutoCompleteTextView.text.toString()
                val description = descriptionTransactionInput.text.toString()

                val transaction = Transaction(value, bank, category, description, 0)

                val id = transactionDbHelper.insertTransaction(transaction)

                if (id != null) {
                    Toast.makeText(this, "Transação incluída!", Toast.LENGTH_SHORT)
                        .show()

                    cleanFields()
                } else {
                    Toast.makeText(
                        this,
                        "Houve um erro ao inserir sua transação ;(",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }

        expenseTransactionButton.setOnClickListener {
            if (!isFieldsFilled()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val value = valueTransactionInput.text.toString().toDouble()
                val bank = bankOptionAutoCompleteTextView.text.toString()
                val category = categoryOptionsAutoCompleteTextView.text.toString()
                val description = descriptionTransactionInput.text.toString()

                val transaction = Transaction(value, bank, category, description, 1)

                val id = transactionDbHelper.insertTransaction(transaction)

                if (id != null) {
                    Toast.makeText(this, "Transação incluída!", Toast.LENGTH_SHORT)
                        .show()

                    cleanFields()
                } else {
                    Toast.makeText(
                        this,
                        "Houve um erro ao inserir sua transação ;(",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm =
                this@HomeActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this@HomeActivity.currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun onIncludeTransactionButtonClicked() {
        setVisibility(includeTransactionButtonClicked)
        setAnimation(includeTransactionButtonClicked)
        includeTransactionButtonClicked = !includeTransactionButtonClicked
    }

    private fun setVisibility(clicked: Boolean) {
        val incomeTransactionButton =
            findViewById<FloatingActionButton>(R.id.incomeTransactionButton)
        val expenseTransactionButton =
            findViewById<FloatingActionButton>(R.id.expenseTransactionButton)

        if (!clicked) {
            incomeTransactionButton.visibility = View.VISIBLE
            expenseTransactionButton.visibility = View.VISIBLE
        } else {
            incomeTransactionButton.visibility = View.INVISIBLE
            expenseTransactionButton.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        val includeTransactionButton =
            findViewById<FloatingActionButton>(R.id.includeTransactionButton)
        val incomeTransactionButton =
            findViewById<FloatingActionButton>(R.id.incomeTransactionButton)
        val expenseTransactionButton =
            findViewById<FloatingActionButton>(R.id.expenseTransactionButton)

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