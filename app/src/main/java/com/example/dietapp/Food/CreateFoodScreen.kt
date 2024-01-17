package com.example.dietapp.Food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dietapp.MainActivityModel
import com.example.dietapp.R
import com.example.dietapp.backend.Product

class CreateFoodScreen: Fragment() {

    private lateinit var leftSearchView: TextView
    private lateinit var leftRecyclerView: RecyclerView
    private lateinit var leftDetailsView: TextView
    private lateinit var leftAmountSelector: EditText
    private lateinit var rightSearchView: TextView
    private lateinit var rightRecyclerView: RecyclerView
    private lateinit var rightDetailsView: TextView
    private lateinit var saveButton: Button
    private lateinit var backButton: Button

    private lateinit var mainActivityModel: MainActivityModel

    private val chosenProductsWithPortions: MutableList<Pair<Product,Float>> = arrayListOf<Pair<Product,Float>>()

    private var test = "abc"

    private lateinit var availableProductsAdapter: AvailableProductsListAdapter
    private lateinit var usedProductsAdapter: UsedProductsListAdapter

    private lateinit var selectedProduct: Product
    private var filter = ""
    private var leftButtons: MutableList<Button> = arrayListOf<Button>()
    private var rightButtons: MutableList<Button> = arrayListOf<Button>()
    private var pageNumber = 0
    private val FIELDS_ON_PAGE = 4

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()
        val view = inflater.inflate(R.layout.create_food_screen, container, false)
        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)

        leftSearchView = view.findViewById(R.id.LeftSearchView)
        leftRecyclerView = view.findViewById(R.id.LeftRecyclerView)
        leftDetailsView = view.findViewById(R.id.LeftDetailsView)
        leftAmountSelector = view.findViewById(R.id.LeftAmountSelector)
        rightSearchView = view.findViewById(R.id.RightSearchView)
        rightRecyclerView = view.findViewById(R.id.RightRecyclerView)
        rightDetailsView = view.findViewById(R.id.RightDetailsView)
        saveButton = view.findViewById(R.id.SaveButton)
        backButton = view.findViewById(R.id.BackButton)


        availableProductsAdapter =  AvailableProductsListAdapter(mainActivityModel.products, ::useProduct)
        leftRecyclerView.layoutManager = LinearLayoutManager(context)
        leftRecyclerView.adapter = availableProductsAdapter

        usedProductsAdapter = UsedProductsListAdapter(chosenProductsWithPortions)
        rightRecyclerView.layoutManager = LinearLayoutManager(context)
        rightRecyclerView.adapter = usedProductsAdapter

        return view
    }

    fun useProduct(product: Product){
        //TODO dialog or sth to choose portion size
        var portion = 1f
        val newItem = Pair(product,portion)
        chosenProductsWithPortions.add(newItem)
        usedProductsAdapter.notifyDataSetChanged()
    }

}