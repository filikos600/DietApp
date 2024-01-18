package com.example.dietapp.Activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dietapp.R
import com.example.dietapp.backend.Activity

class ActivityListAdapter(private val items: MutableList<Activity>, private val ahowActivityInfo: (input: Activity) -> Unit):

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
            ahowActivityInfo(item)
        }

        holder.buttonEdit.setOnClickListener {
            //TODO editing thing
            Toast.makeText(it.context,"editing soon TM", Toast.LENGTH_SHORT).show()
        }

        holder.buttonRemove.setOnClickListener {
            items.removeAt(holder.adapterPosition)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}