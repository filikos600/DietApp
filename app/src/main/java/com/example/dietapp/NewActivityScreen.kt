package com.example.dietapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.internal.ContextUtils.getActivity

class NewActivityScreen : AppCompatActivity() {

    private lateinit var addActivity: Button
    private lateinit var cancel: Button
    private lateinit var name: EditText
    private lateinit var desc: EditText
    private lateinit var kcalReduction: EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.new_activity_screen)

        addActivity = findViewById(R.id.add)
        cancel = findViewById(R.id.cancel)
        name = findViewById(R.id.activityName)
        desc = findViewById(R.id.activityDesc)
        kcalReduction = findViewById(R.id.kcalReduction)

        addActivity.setOnClickListener {
            var result = "name: " + name.text.toString() + " desc: " + desc.text.toString() + " kcal usage: " + kcalReduction.text.toString()
            Toast.makeText(applicationContext,result, Toast.LENGTH_SHORT).show()
        }

        cancel.setOnClickListener {
            Toast.makeText(applicationContext,"cancel is pressed", Toast.LENGTH_SHORT).show()
        }

    }
}