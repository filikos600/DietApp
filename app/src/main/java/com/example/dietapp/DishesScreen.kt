package com.example.dietapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DishesScreen : AppCompatActivity() {

    private lateinit var addBasicIngredient: Button
    private lateinit var addDish: Button
    private lateinit var daySummary: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dishes_screen)

        addBasicIngredient = findViewById(R.id.addBasicIngredient)
        addDish = findViewById(R.id.addDish)
        daySummary = findViewById(R.id.daySummary)

        addBasicIngredient.setOnClickListener {
            daySummary.text = "Adding basic ingredient (WIP)"
        }

        addDish.setOnClickListener {
            daySummary.text = "Adding dish (WIP)"
        }

    }
}