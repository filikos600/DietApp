package com.example.dietapp.Food

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
import com.example.dietapp.Products.ProductsListAdapter
import com.example.dietapp.R
import com.example.dietapp.backend.Food
import com.example.dietapp.backend.Product


class FoodScreen  : Fragment(){
    private lateinit var searchView: TextView
    private lateinit var addNewFoodButton: Button
    private lateinit var foodRecycler: RecyclerView
    private lateinit var detailsView: TextView
    private lateinit var imageView: TextView
    private lateinit var amountSelector: EditText
    private lateinit var addButton: Button

    private lateinit var selectedFood: Food
    private lateinit var mainActivityModel: MainActivityModel

    private var filter = ""
    private var buttons: MutableList<Button> = arrayListOf<Button>()
    private var pageNumber = 0
    private var foods: MutableList<Food> = arrayListOf<Food>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.food_screen, container, false)

        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)

        searchView = view.findViewById(R.id.SearchView)
        addNewFoodButton = view.findViewById(R.id.AddNewFoodButton)
        foodRecycler = view.findViewById(R.id.FoodRecycler)
        detailsView = view.findViewById(R.id.DetailsView)
        imageView = view.findViewById(R.id.ImageView)
        amountSelector = view.findViewById(R.id.AmountSelector)
        addButton = view.findViewById(R.id.AddButton)


        foodRecycler.layoutManager = LinearLayoutManager(context)
        foodRecycler.adapter = FoodListAdapter(mainActivityModel.foods, ::showFoodInfo)

        addNewFoodButton.setOnClickListener {
            (activity as? MainActivityInterface)?.foodsToCreateFoodButton()
        }

        addButton.setOnClickListener {
            if(::selectedFood.isInitialized && amountSelector.text.isNotBlank()) {
                var amount = amountSelector.text.toString().toInt()
                mainActivityModel.user.AddFood(amount, selectedFood)
            }

        }
        return view
    }

    fun showFoodInfo(food: Food){
        selectedFood = food
        detailsView.text = food.printDishInfo()
    }

}