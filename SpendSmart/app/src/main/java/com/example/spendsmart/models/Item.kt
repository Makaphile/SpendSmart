package com.example.spendsmart.models

data class Item(
    val category: String,
    val description: String,
    val date: String,
    val image: ByteArray? = null
)
