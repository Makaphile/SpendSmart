package com.example.spendsmart.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {
    @Insert
    fun insertItem(item: ItemEntity)

    @Query("SELECT * FROM items")
    fun getAllItems(): List<ItemEntity>
}
