package com.example.spendsmart.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val description: String,
    val date: String,
    val image: ByteArray?
)
