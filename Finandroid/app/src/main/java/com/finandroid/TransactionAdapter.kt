package com.finandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finandroid.models.TransactionResponse


class TransactionAdapter(
    private val context: Context,
    private val itens: MutableList<TransactionResponse>
) : RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    inner class TransactionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category = itemView.findViewById<TextView>(R.id.category)
        val value = itemView.findViewById<TextView>(R.id.value)
        val account = itemView.findViewById<TextView>(R.id.account)
        val description = itemView.findViewById<TextView>(R.id.description)
        val imagem = itemView.findViewById<ImageView>(R.id.imagemCategoria)
    }

    // cria as view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val itemLista =
            LayoutInflater.from(context).inflate(R.layout.transation_layout, parent, false)
        val holder = TransactionHolder(itemLista)
        return holder
    }

    // exibe as view
    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        holder.category.text = itens[position].category
        holder.description.text = itens[position].description
        holder.account.text = itens[position].bank
        val valueTransaction = itens[position].value.toString()
        holder.value.text = "R$ $valueTransaction"

        if (itens[position].flow == 0) {
            holder.imagem.setImageResource(R.drawable.ic_trending_up)
        } else {
            holder.imagem.setImageResource(R.drawable.ic_trending_down)
        }
    }

    override fun getItemCount(): Int = itens.size
}
