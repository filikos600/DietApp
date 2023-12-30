package com.example.dietapp

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity

class MainScreen : AppCompatActivity() {

    private lateinit var addDish: Button
    private lateinit var addActivity: Button
    private lateinit var summary: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_screen)

        addDish = findViewById(R.id.addDish)
        addActivity = findViewById(R.id.addActivity)
        summary = findViewById(R.id.summary)

        addDish.setOnClickListener {
            summary.text = "Adding dish (WIP)"
        }

        addActivity.setOnClickListener {
            summary.text = "Adding activity (WIP)"
        }

    }
}