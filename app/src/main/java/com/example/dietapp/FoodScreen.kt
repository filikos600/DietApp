package com.example.dietapp

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dietapp.CreateProductScreen


class FoodScreen  : Fragment(){
    private lateinit var searchView: TextView
    private lateinit var addProductButton: Button
    private lateinit var productsLayout: LinearLayout
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var detailsView: TextView
    private lateinit var imageView: TextView
    private lateinit var amountSelector: EditText
    private lateinit var addButton: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.food_screen, container, false)

        searchView = view.findViewById(R.id.SearchView)
        addProductButton = view.findViewById(R.id.AddProductButton)
        productsLayout = view.findViewById(R.id.ProductsLayout)
        previousButton = view.findViewById(R.id.PreviousButton)
        nextButton = view.findViewById(R.id.NextButton)
        detailsView = view.findViewById(R.id.DetailsView)
        imageView = view.findViewById(R.id.ImageView)
        amountSelector = view.findViewById(R.id.AmountSelector)
        addButton = view.findViewById(R.id.AddButton)

        searchView.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                // Perform your action here
                Toast.makeText(requireContext(),"searching for " + searchView.text.toString(), Toast.LENGTH_SHORT).show()

                // Return true to consume the event
                true
            } else {
                // Return false to let the system handle the event
                false
            }
        }

        addProductButton.setOnClickListener {
            val fragment = CreateProductScreen()
            val fragmentManager = activity?.supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.foodScreen, fragment)?.addToBackStack(null)?.commit()
        }

        previousButton.setOnClickListener {
            Toast.makeText(requireContext(),"switch to previous page on list", Toast.LENGTH_SHORT).show()
        }

        nextButton.setOnClickListener {
            Toast.makeText(requireContext(),"switch to next page on list", Toast.LENGTH_SHORT).show()
        }

        addButton.setOnClickListener {
            var amount = amountSelector.text.toString()
            Toast.makeText(requireContext(),"added $amount of product", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}