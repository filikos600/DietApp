package com.example.dietapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class CreateProductScreen  : AppCompatActivity(){
    private lateinit var imageView: TextView
    private lateinit var nameEdit: EditText
    private lateinit var caloriesEdit: EditText
    private lateinit var portionEdit: EditText
    private lateinit var nutricionalValuesView: TextView
    private lateinit var addButton: Button
    private lateinit var backButton: Button


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_product_screen)

        imageView = findViewById(R.id.ImageEdit)
        nameEdit = findViewById(R.id.NameEdit)
        caloriesEdit = findViewById(R.id.CaloriesEdit)
        portionEdit = findViewById(R.id.PortionEdit)
        nutricionalValuesView = findViewById(R.id.NutricionalValuesView)
        addButton = findViewById(R.id.AddButton)
        backButton = findViewById(R.id.BackButton)


        addButton.setOnClickListener {

            var name = nameEdit.text.toString().trim()
            var calories = caloriesEdit.text.toString().trim()
            var portion = portionEdit.text.toString().trim()

            if (name.isEmpty() || calories.isEmpty() || portion.isEmpty()){
                Toast.makeText(applicationContext,"Please fill all info", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext,"added $name product, $calories calories and $portion portion", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}