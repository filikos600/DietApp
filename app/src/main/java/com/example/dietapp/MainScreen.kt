package com.example.dietapp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainScreen : Fragment() {

    private lateinit var addDish: Button
    private lateinit var addActivity: Button
    private lateinit var summary: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.main_screen, container, false)

        addDish = view.findViewById(R.id.addDish)
        addActivity = view.findViewById(R.id.addActivity)
        summary = view.findViewById(R.id.summary)

        addDish.setOnClickListener {
            summary.text = "Adding dish (WIP)"
        }

        addActivity.setOnClickListener {
            summary.text = "Adding activity (WIP)"
        }
    return view
    }
}