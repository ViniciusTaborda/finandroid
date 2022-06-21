package com.finandroid.models

class Transaction {
    val value: Double;
    val bank: String;
    val category: String;
    val description: String;
    val flow: Int;

    constructor(value: Double, bank: String, category: String, description: String, flow: Int) {
        this.value = value
        this.bank = bank
        this.category = category
        this.description = description
        this.flow = flow
    }
}

class TransactionResponse {
    val id: Long;
    val value: Double;
    val bank: String;
    val category: String;
    val description: String;
    val flow: Int;

    constructor(
        id: Long,
        value: Double,
        bank: String,
        category: String,
        description: String,
        flow: Int
    ) {
        this.id = id
        this.value = value
        this.bank = bank
        this.category = category
        this.description = description
        this.flow = flow
    }
}