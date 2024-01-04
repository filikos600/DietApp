package com.example.dietapp.com.example.dietapp

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dietapp.R

class ProductsScreen  : AppCompatActivity(){
    private lateinit var searchView: TextView
    private lateinit var addProductButton: Button
    private lateinit var productsLayout: LinearLayout
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var detailsView: TextView
    private lateinit var imageView: TextView
    private lateinit var amountSelector: EditText
    private lateinit var addButton: Button
    

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.products_screen)

        searchView = findViewById(R.id.SearchView)
        addProductButton = findViewById(R.id.AddProductButton)
        productsLayout = findViewById(R.id.ProductsLayout)
        previousButton = findViewById(R.id.PreviousButton)
        nextButton = findViewById(R.id.NextButton)
        detailsView = findViewById(R.id.DetailsView)
        imageView = findViewById(R.id.ImageView)
        amountSelector = findViewById(R.id.AmountSelector)
        addButton = findViewById(R.id.AddButton)

        searchView.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                // Perform your action here
                Toast.makeText(applicationContext,"searching for " + searchView.text.toString(), Toast.LENGTH_SHORT).show()

                // Return true to consume the event
                true
            } else {
                // Return false to let the system handle the event
                false
            }
        }

        addProductButton.setOnClickListener {
            val intent = Intent(this, CreateProductScreen::class.java)
            startActivity(intent)
        }

        previousButton.setOnClickListener {
            Toast.makeText(applicationContext,"switch to previous page on list", Toast.LENGTH_SHORT).show()
        }

        nextButton.setOnClickListener {
            Toast.makeText(applicationContext,"switch to next page on list", Toast.LENGTH_SHORT).show()
        }

        addButton.setOnClickListener {
            var amount = amountSelector.text.toString()
            Toast.makeText(applicationContext,"added $amount of product", Toast.LENGTH_SHORT).show()
        }
    }
}