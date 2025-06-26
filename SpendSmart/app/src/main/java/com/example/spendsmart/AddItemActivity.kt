     package com.example.spendsmart
     
     import android.app.Activity
     import android.content.Intent
     import android.graphics.Bitmap
     import android.os.Bundle
     import android.provider.MediaStore
     import android.widget.*
     import androidx.appcompat.app.AppCompatActivity
     import com.example.spendsmart.database.AppDatabase
     import com.example.spendsmart.database.ItemEntity
     import java.io.ByteArrayOutputStream
     
     class AddItemActivity : AppCompatActivity() {
     
         private lateinit var btnSaveItem: Button
         private lateinit var etCategory: EditText
         private lateinit var etDescription: EditText
         private lateinit var tvDate: TextView
         private lateinit var ivPhoto: ImageView
         private lateinit var btnCapture: Button
     
         private var itemImage: Bitmap? = null
         private var selectedDate: String = "2025-06-24"
     
         private val CAMERA_REQUEST_CODE = 101
     
         override fun onCreate(savedInstanceState: Bundle?) {
             super.onCreate(savedInstanceState)
             setContentView(R.layout.activity_add_item)
     
             btnSaveItem = findViewById(R.id.btnSaveItem)
             etCategory = findViewById(R.id.etCategory)
             etDescription = findViewById(R.id.etDescription)
             tvDate = findViewById(R.id.tvDate)
             ivPhoto = findViewById(R.id.ivPhoto)
             btnCapture = findViewById(R.id.btnCapture)
     
             btnCapture.setOnClickListener {
                 val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                 startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
             }
     
             btnSaveItem.setOnClickListener {
                 val category = etCategory.text.toString().trim()
                 val description = etDescription.text.toString().trim()
     
                 if (category.isNotEmpty() && description.isNotEmpty()) {
                     val imageBytes = itemImage?.let {
                         val stream = ByteArrayOutputStream()
                         it.compress(Bitmap.CompressFormat.PNG, 100, stream)
                         stream.toByteArray()
                     }
     
                     val item = ItemEntity(
                         category = category,
                         description = description,
                         date = selectedDate,
                         image = imageBytes
                     )
     
                     Thread {
                         AppDatabase.getDatabase(this).itemDao().insertItem(item)
                         runOnUiThread {
                             Toast.makeText(this, "Item saved", Toast.LENGTH_SHORT).show()
                             etCategory.text.clear()
                             etDescription.text.clear()
                             ivPhoto.setImageBitmap(null)
                             itemImage = null
                         }
                     }.start()
                 } else {
                     Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                 }
             }
         }
     
         override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
             if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                 itemImage = data?.extras?.get("data") as Bitmap
                 ivPhoto.setImageBitmap(itemImage)
             }
             super.onActivityResult(requestCode, resultCode, data)
         }
     }
