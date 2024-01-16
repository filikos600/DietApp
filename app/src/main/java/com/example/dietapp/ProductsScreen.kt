package com.example.dietapp

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
import androidx.fragment.app.Fragment
import com.example.dietapp.backend.Product
import com.example.dietapp.backend.User

class ProductsScreen  : Fragment(){
    private lateinit var searchView: TextView
    private lateinit var addProductButton: Button
    private lateinit var productsLayout: LinearLayout
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var detailsView: TextView
    private lateinit var imageView: TextView
    private lateinit var amountSelector: EditText
    private lateinit var addButton: Button

    private lateinit var user: User
    private lateinit var selectedProduct: Product

    private var filter = ""
    private var buttons: ArrayList<Button> = arrayListOf<Button>()
    private var pageNumber = 0
    private var products: ArrayList<Product> = arrayListOf<Product>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.products_screen, container, false)

        searchView = view.findViewById(R.id.SearchView)
        addProductButton = view.findViewById(R.id.AddProductButton)
        productsLayout = view.findViewById(R.id.ProductsLayout)
        previousButton = view.findViewById(R.id.PreviousButton)
        nextButton = view.findViewById(R.id.NextButton)
        detailsView = view.findViewById(R.id.DetailsView)
        imageView = view.findViewById(R.id.ImageView)
        amountSelector = view.findViewById(R.id.AmountSelector)
        addButton = view.findViewById(R.id.AddButton)

        for(i in 0..5)
        {
            val button = Button(requireContext())
            button.setOnClickListener {
                try {
                    val index = products.indexOfFirst { product -> product.name == button.text }
                    val product = products.get(index)
                    selectedProduct = product
                    detailsView.text = product.printProductInfo()
                }
                catch(_: Exception)
                {

                }
            }
            buttons.add(button)
            val params = LinearLayout.LayoutParams(0,100)
            params.weight = 1f
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            productsLayout.addView(button, params)
        }
        setButtonsForProducts(products, 0)

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
            (activity as? MainActivityInterface)?.productsToCreateProductButton()
        }

        previousButton.setOnClickListener {
            val filteredActivities = findProducts(filter)
            if(pageNumber > 0)
                pageNumber -= 1
            setButtonsForProducts(filteredActivities,pageNumber)
        }

        nextButton.setOnClickListener {
            val filteredActivities = findProducts(filter)
            if(pageNumber < filteredActivities.size/6)
                pageNumber += 1
            setButtonsForProducts(filteredActivities,pageNumber)
        }

        addButton.setOnClickListener {
            var amount = amountSelector.text.toString()
            Toast.makeText(requireContext(),"added $amount of product", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    fun findProducts(name: String): ArrayList<Product>
    {
        if(name.isBlank())
            return products
        val foundProduct: ArrayList<Product> = arrayListOf<Product>()
        for(product in products)
        {
            if(product.name.startsWith(name, true))
                foundProduct.add(product)
        }
        return foundProduct
    }

    fun setButtonsForProducts(products: ArrayList<Product>, pageNumber: Int)
    {
        for(i in 0..5)
        {
            var button = buttons[i]
            button.setVisibility(View.VISIBLE)
            try {
                button.text = products[pageNumber*6 + i].name
            }
            catch(_: Exception)
            {
                button.setVisibility(View.INVISIBLE)
            }
        }
    }
}