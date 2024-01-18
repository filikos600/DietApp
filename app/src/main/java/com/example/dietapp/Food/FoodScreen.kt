package com.example.dietapp.Food

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.dietapp.Products.ProductsListAdapter
import com.example.dietapp.R
import com.example.dietapp.backend.Food
import com.example.dietapp.backend.Product


class FoodScreen  : Fragment(){
    private lateinit var searchEdit: EditText
    private lateinit var addNewFoodButton: Button
    private lateinit var foodRecycler: RecyclerView
    private lateinit var detailsView: TextView
    private lateinit var imageView: TextView
    private lateinit var amountSelector: EditText
    private lateinit var addButton: Button

    private lateinit var selectedFood: Food
    private lateinit var mainActivityModel: MainActivityModel

    private lateinit var filteredItems: MutableList<Food>
    private lateinit var adapter: FoodListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.food_screen, container, false)

        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)
        filteredItems = mainActivityModel.foods.toMutableList()

        searchEdit = view.findViewById(R.id.SearchEdit)
        addNewFoodButton = view.findViewById(R.id.AddNewFoodButton)
        foodRecycler = view.findViewById(R.id.FoodRecycler)
        detailsView = view.findViewById(R.id.DetailsView)
        imageView = view.findViewById(R.id.ImageView)
        amountSelector = view.findViewById(R.id.AmountSelector)
        addButton = view.findViewById(R.id.AddButton)

        foodRecycler.layoutManager = LinearLayoutManager(context)
        adapter = FoodListAdapter(filteredItems, ::showFoodInfo)
        foodRecycler.adapter = adapter

        addNewFoodButton.setOnClickListener {
            (activity as? MainActivityInterface)?.foodsToCreateFoodButton()
        }

        addButton.setOnClickListener {
            if(::selectedFood.isInitialized && amountSelector.text.isNotBlank()) {
                var amount = amountSelector.text.toString().toInt()
                mainActivityModel.user.AddFood(amount, selectedFood)
            }

        }

        searchEdit.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchRecyclerView(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        return view
    }

    fun searchRecyclerView(searchValue: String) {
        filteredItems = mainActivityModel.foods.toMutableList()
        if (searchValue.isNotBlank()){
            filteredItems = filteredItems.filter { item ->
                item.name.contains(searchValue, ignoreCase = true)
            }.toMutableList()
        }
        adapter.setFilteredItems(filteredItems)
    }

    fun showFoodInfo(food: Food){
        selectedFood = food
        detailsView.text = food.printDishInfo()
    }

}