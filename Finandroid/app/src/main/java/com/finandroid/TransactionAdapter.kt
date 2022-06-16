package com.finandroid


import android.annotation.SuppressLint
import android.content.Context
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TransactionAdapter(private val context: Context, private val itens: MutableList<Transaction>): RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    inner class TransactionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category = itemView.findViewById<TextView>(R.id.category)
        val value = itemView.findViewById<TextView>(R.id.value)
        val account = itemView.findViewById<TextView>(R.id.account)
        val description = itemView.findViewById<TextView>(R.id.description)
        val imagem = itemView.findViewById<ImageView>(R.id.imagemCategoria)
        val flow = 1

    }

    // cria as view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val itemLista = LayoutInflater.from(context).inflate(R.layout.transation_layout,parent,false)
        val holder = TransactionHolder(itemLista)
        return holder
    }

    // exibe as view
    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        holder.category.text = itens[position].category
        holder.description.text = itens[position].description
        holder.account.text = itens[position].account
        holder.value.text = itens[position].value
        holder.imagem.setImageResource(itens[position].imagem)

    }

    override fun getItemCount(): Int = itens.size


}