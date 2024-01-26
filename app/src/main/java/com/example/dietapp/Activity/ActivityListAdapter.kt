package com.example.dietapp.Activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dietapp.R
import com.example.dietapp.backend.Activity

class ActivityListAdapter(private val items: MutableList<Activity>, private val useActivity: (input: Activity) -> Unit,  private val editActivity:(input: Int) ->Unit):
    RecyclerView.Adapter<ActivityListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewItem: TextView = itemView.findViewById(R.id.textViewItem)
        val buttonEdit: Button = itemView.findViewById(R.id.buttonEdit)
        val buttonRemove: Button = itemView.findViewById(R.id.buttonRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.textViewItem.text = item.name

        holder.textViewItem.setOnClickListener{
            useActivity(item)
        }

        holder.buttonEdit.setOnClickListener {
            editActivity(position)
        }

        holder.buttonRemove.setOnClickListener {
            items.removeAt(holder.adapterPosition)
            notifyDataSetChanged()
        }
    }

    fun setFilteredItems(filteredItems: MutableList<Activity>) {
        items.clear()
        items.addAll(filteredItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    public fun getItems(): MutableList<Activity>{
        return items
    }
}