package com.example.dietapp.Food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dietapp.R
import com.example.dietapp.backend.Product

class AvailableProductsListAdapter(private val items: MutableList<Product>, private val useProduct: (input: Product) -> Unit) :
    RecyclerView.Adapter<AvailableProductsListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewItem: TextView = itemView.findViewById(R.id.textViewItem)
        val buttonEdit: Button = itemView.findViewById(R.id.buttonEdit)
        val buttonAdd: Button = itemView.findViewById(R.id.buttonAdd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.available_product_list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        holder.textViewItem.text = item.name

        val itemName = item.name
        holder.buttonEdit.setOnClickListener {
            Toast.makeText(it.context,"edited $itemName product", Toast.LENGTH_SHORT).show()
        }

        holder.buttonAdd.setOnClickListener {
            useProduct(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}