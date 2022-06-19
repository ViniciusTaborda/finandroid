package com.finandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageButton>(R.id.bListaTransacao).setOnClickListener{
            goListTransaction()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        //configurar adapter

        val itens: MutableList<Transaction> = mutableListOf()

        val adapterTransaction = TransactionAdapter(this, itens)
        recyclerView.adapter = adapterTransaction

        val transacao1 = Transaction(
            "Pesadelo",
            "R$300,00",
            "Bradesco",
            "Show MC Mirella",
            1,
            R.drawable.ic_food
        )
        itens.add(transacao1)

        val transacao2 = Transaction(
            "Alimentação",
            "R$80,00",
            "Bradesco",
            "Pizza de sábado a noite",
            1,
            R.drawable.ic_food
        )
        itens.add(transacao2)



    }
    private fun goListTransaction(){
        val listTransaction = Intent(this, Transaction::class.java)
        startActivity(listTransaction)
    }

}


