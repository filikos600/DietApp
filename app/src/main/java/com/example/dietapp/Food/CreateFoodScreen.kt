package com.example.dietapp.Food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dietapp.Main.MainActivityInterface
import com.example.dietapp.Main.MainActivityModel
import com.example.dietapp.R
import com.example.dietapp.backend.Food
import com.example.dietapp.backend.Product

class CreateFoodScreen: Fragment() {

    private lateinit var foodName: EditText
    private lateinit var leftSearchView: TextView
    private lateinit var leftRecyclerView: RecyclerView
    private lateinit var leftDetailsView: TextView
    private lateinit var rightSearchView: TextView
    private lateinit var rightRecyclerView: RecyclerView
    private lateinit var rightDetailsView: TextView
    private lateinit var saveButton: Button
    private lateinit var backButton: Button

    private lateinit var mainActivityModel: MainActivityModel

    private lateinit var chosenProductsWithPortions: MutableList<Pair<Product,Float>>

    private lateinit var availableProductsAdapter: AvailableProductsListAdapter
    private lateinit var usedProductsAdapter: UsedProductsListAdapter

    private var editing = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()
        val view = inflater.inflate(R.layout.create_food_screen, container, false)
        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)

        foodName = view.findViewById(R.id.FoodName)
        leftSearchView = view.findViewById(R.id.LeftSearchView)
        leftRecyclerView = view.findViewById(R.id.LeftRecyclerView)
        leftDetailsView = view.findViewById(R.id.LeftDetailsView)
        rightSearchView = view.findViewById(R.id.RightSearchView)
        rightRecyclerView = view.findViewById(R.id.RightRecyclerView)
        rightDetailsView = view.findViewById(R.id.RightDetailsView)
        saveButton = view.findViewById(R.id.SaveButton)
        backButton = view.findViewById(R.id.BackButton)


        availableProductsAdapter =  AvailableProductsListAdapter(mainActivityModel.products, ::useProduct)
        leftRecyclerView.layoutManager = LinearLayoutManager(context)
        leftRecyclerView.adapter = availableProductsAdapter


        chosenProductsWithPortions = loadEditedFoodInfo()

        usedProductsAdapter = UsedProductsListAdapter(chosenProductsWithPortions)
        rightRecyclerView.layoutManager = LinearLayoutManager(context)
        rightRecyclerView.adapter = usedProductsAdapter

        saveButton.setOnClickListener {

            val name = foodName.text.toString().trim()
            val productsList = usedProductsAdapter.getItems()

            if (name.isEmpty() || productsList.isEmpty() ){
                Toast.makeText(requireContext(),"Name and products are required", Toast.LENGTH_SHORT).show()
            } else {
                if (editing){
                    mainActivityModel.foods[mainActivityModel.editedFoodIndex] = Food(name,productsList)
                    mainActivityModel.editedFoodIndex = -1
                    (activity as? MainActivityInterface)?.createFoodtoFoods()
                }
                else{
                    mainActivityModel.foods.add(Food(name,productsList))
                    (activity as? MainActivityInterface)?.createFoodtoFoods()
                }

            }
        }

        return view
    }

    fun useProduct(product: Product){
        val dialog = ProductQuantityDialog(this, product)
        dialog.show(parentFragmentManager, "ProductQuantityDialog")
    }

    fun onNumberChosen(number: Float, product: Product) {
        var portion = number
        val newItem = Pair(product,portion)
        chosenProductsWithPortions.add(newItem)
        usedProductsAdapter.notifyDataSetChanged()
        println("Chosen number: $number")
    }

    fun loadEditedFoodInfo() : MutableList<Pair<Product,Float>>{
        val index = mainActivityModel.editedFoodIndex
        if (index < 0){
            editing = false
            return mutableListOf()
        }
        else{
            val editFood =  mainActivityModel.foods[index]
            editing = true
            foodName.setText(editFood.name)
            return editFood.list_of_product
        }
    }

}