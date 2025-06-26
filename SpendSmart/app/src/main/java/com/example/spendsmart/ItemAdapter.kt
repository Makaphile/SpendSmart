package com.example.spendsmart.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.spendsmart.R
import com.example.spendsmart.models.Item

class ItemAdapter(private val context: Context, private val items: List<Item>) : BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Item = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_list_row, parent, false)

        val tvCategory = view.findViewById<TextView>(R.id.tvCategory)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        val tvDate = view.findViewById<TextView>(R.id.tvDate)
        val ivImage = view.findViewById<ImageView>(R.id.ivItemImage)

        tvCategory.text = item.category
        tvDescription.text = item.description
        tvDate.text = item.date

        if (item.image != null) {
            val bitmap = BitmapFactory.decodeByteArray(item.image, 0, item.image.size)
            ivImage.setImageBitmap(bitmap)
        } else {
            ivImage.setImageResource(android.R.drawable.ic_menu_report_image)
        }

        return view
    }
}
