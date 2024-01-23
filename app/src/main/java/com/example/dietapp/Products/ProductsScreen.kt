package com.example.dietapp.Products

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dietapp.Main.MainActivityInterface
import com.example.dietapp.Main.MainActivityModel
import com.example.dietapp.R
import com.example.dietapp.backend.Food
import com.example.dietapp.backend.Product

class ProductsScreen  : Fragment(){
    private lateinit var searchEdit: EditText
    private lateinit var addProductButton: Button
    private lateinit var productsRecycler: RecyclerView
    private lateinit var detailsView: TextView
    private lateinit var amountSelector: EditText
    private lateinit var addButton: Button

    private lateinit var selectedProduct: Product
    private lateinit var mainActivityModel: MainActivityModel
    private lateinit var filteredItems: MutableList<Product>
    private lateinit var adapter: ProductsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.products_screen, container, false)

        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)
        filteredItems = mainActivityModel.products.toMutableList()
        adapter = ProductsListAdapter(filteredItems, ::showProductInfo, ::editProduct)

        searchEdit = view.findViewById(R.id.SearchEdit)
        addProductButton = view.findViewById(R.id.AddProductButton)
        productsRecycler = view.findViewById(R.id.ProductsRecycler)
        detailsView = view.findViewById(R.id.DetailsView)
        amountSelector = view.findViewById(R.id.AmountSelector)
        addButton = view.findViewById(R.id.AddButton)

        productsRecycler.layoutManager = LinearLayoutManager(context)
        productsRecycler.adapter = adapter

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

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as? MainActivityInterface)?.backToMainButton()
            }
        })

        searchEdit.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchRecyclerView(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        return view
    }

    override fun onStop() {
        mainActivityModel.products = adapter.getItems()
        super.onStop()
    }

    fun searchRecyclerView(searchValue: String) {
        filteredItems = mainActivityModel.products.toMutableList()
        if (searchValue.isNotBlank()){
            filteredItems = filteredItems.filter { item ->
                item.name.contains(searchValue, ignoreCase = true)
            }.toMutableList()
        }
        adapter.setFilteredItems(filteredItems)
    }

    fun showProductInfo(product: Product){
        selectedProduct = product
        detailsView.text = product.printProductInfo()
    }

    fun editProduct(productIndex: Int){
        mainActivityModel.editedProductIndex = productIndex
        (activity as? MainActivityInterface)?.productsToCreateProductButton()
    }
}