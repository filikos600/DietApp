package com.example.dietapp.Products

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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dietapp.MainActivityInterface
import com.example.dietapp.MainActivityModel
import com.example.dietapp.R
import com.example.dietapp.backend.Product
import com.example.dietapp.backend.User

class ProductsScreen  : Fragment(){
    private lateinit var searchView: TextView
    private lateinit var addProductButton: Button
    private lateinit var productsRecycler: RecyclerView
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var detailsView: TextView
    private lateinit var amountSelector: EditText
    private lateinit var addButton: Button

    private lateinit var user: User
    private lateinit var selectedProduct: Product

    private var filter = ""
    private var buttons: MutableList<Button> = arrayListOf<Button>()
    private var pageNumber = 0

    private val FIELDS_ON_PAGE = 5

    private lateinit var mainActivityModel: MainActivityModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.products_screen, container, false)

        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)

        searchView = view.findViewById(R.id.SearchView)
        addProductButton = view.findViewById(R.id.AddProductButton)
        productsRecycler = view.findViewById(R.id.ProductsRecycler)
        previousButton = view.findViewById(R.id.PreviousButton)
        nextButton = view.findViewById(R.id.NextButton)
        detailsView = view.findViewById(R.id.DetailsView)
        amountSelector = view.findViewById(R.id.AmountSelector)
        addButton = view.findViewById(R.id.AddButton)

        productsRecycler.layoutManager = LinearLayoutManager(context)
        productsRecycler.adapter = ProductsListAdapter(mainActivityModel.products, ::showProductInfo)

        addProductButton.setOnClickListener {
            (activity as? MainActivityInterface)?.productsToCreateProductButton()
        }

        addButton.setOnClickListener {
            if(::selectedProduct.isInitialized && amountSelector.text.isNotBlank()) {
                var amount = amountSelector.text.toString().toInt()
                mainActivityModel.user.AddProduct(amount, selectedProduct)
                (activity as? MainActivityInterface)?.backToMainButton()
            }
        }
        return view
    }

    fun showProductInfo(product: Product){
        selectedProduct = product
        detailsView.text = product.printProductInfo()
    }

    fun findProducts(name: String): MutableList<Product>
    {
        if(name.isBlank())
            return mainActivityModel.products
        val foundProduct: MutableList<Product> = arrayListOf<Product>()
        for(product in mainActivityModel.products)
        {
            if(product.name.startsWith(name, true))
                foundProduct.add(product)
        }
        return foundProduct
    }

}