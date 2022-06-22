package com.finandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finandroid.database.TransactionDbHelper
import com.finandroid.models.TransactionResponse

class ListTransaction : AppCompatActivity() {
    private val transactionDbHelper = TransactionDbHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_transaction)

        // Fixando tamanho do recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val transactions: MutableList<TransactionResponse> = transactionDbHelper.readTransactions()

        val adapterTransaction = TransactionAdapter(this, transactions)
        recyclerView.adapter = adapterTransaction
    }
}