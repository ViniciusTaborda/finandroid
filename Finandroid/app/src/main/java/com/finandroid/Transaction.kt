package com.finandroid

data class Transaction (
    val category:String,
    val value: String,
    val account: String,
    val description: String,
    val flow:Int,
    val imagem: Int
)