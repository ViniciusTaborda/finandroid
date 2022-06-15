package com.finandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var transactions: ArrayList<Transaction>
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    val saldo = findViewById<TextView>(R.id.saldo)
    val entradas = findViewById<TextView>(R.id.entradas)
    val saidas = findViewById<TextView>(R.id.saidas)
    val recycleView = findViewById<RecyclerView>(R.id.recycleView)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transactions = arrayListOf(
            Transaction("weekend Budget", 400.00),
            Transaction("Banana", -4.00),
            Transaction("gasolina", -50.00),
            Transaction("BreackFest", -10.00),
        )
        transactionAdapter = TransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this@MainActivity)

        recycleView.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager

        }
        updateDashborad()

    }

    private fun updateDashborad(){
        val totalAmount :Double = transactions.map {it.amount}.sum()
        val budgeAmount: Double = transactions.filter { it.amount>0}.map{it.amount}.sum()
        val expenseAmount: Double = totalAmount - budgeAmount

        saldo.text = "$%2f".format(totalAmount)
        entradas.text = "$%2f".format(budgeAmount)
        saidas.text = "$%2f".format(expenseAmount)
    }
}