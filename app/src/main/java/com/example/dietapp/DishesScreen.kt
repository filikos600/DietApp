package com.example.dietapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class DishesScreen : Fragment() {

    private lateinit var addBasicIngredient: Button
    private lateinit var addDish: Button
    private lateinit var daySummary: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.dishes_screen, container, false)

        addBasicIngredient = view.findViewById(R.id.addBasicIngredient)
        addDish = view.findViewById(R.id.addDish)
        daySummary = view.findViewById(R.id.daySummary)

        addBasicIngredient.setOnClickListener {
            daySummary.text = "Adding basic ingredient (WIP)"
        }

        addDish.setOnClickListener {
            daySummary.text = "Adding dish (WIP)"
        }
    return view
    }
}