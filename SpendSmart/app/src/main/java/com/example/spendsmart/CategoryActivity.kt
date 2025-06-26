package com.example.spendsmart

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val etCategoryName = findViewById<EditText>(R.id.etCategoryName)
        val btnSaveCategory = findViewById<Button>(R.id.btnSaveCategory)

        btnSaveCategory.setOnClickListener {
            val category = etCategoryName.text.toString().trim()
            if (category.isNotEmpty()) {
                Toast.makeText(this, "Category '$category' saved!", Toast.LENGTH_SHORT).show()
                etCategoryName.text.clear()
                // TODO: Save category to DB (we’ll do DB in a later step)
            } else {
                Toast.makeText(this, "Please enter a category name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}