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
import com.example.dietapp.backend.Food
import com.example.dietapp.backend.User
import com.example.dietapp.backend.Product


class FoodScreen  : Fragment(){
    private lateinit var searchView: TextView
    private lateinit var addProductButton: Button
    private lateinit var foodLayout: LinearLayout
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var detailsView: TextView
    private lateinit var imageView: TextView
    private lateinit var amountSelector: EditText
    private lateinit var addButton: Button

    private lateinit var user: User
    private lateinit var selectedFood: Food

    private var filter = ""
    private var buttons: ArrayList<Button> = arrayListOf<Button>()
    private var pageNumber = 0
    private var foods: ArrayList<Food> = arrayListOf<Food>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        createTestFood()

        container?.removeAllViews()

        val view = inflater.inflate(R.layout.food_screen, container, false)

        searchView = view.findViewById(R.id.SearchView)
        addProductButton = view.findViewById(R.id.AddProductButton)
        foodLayout = view.findViewById(R.id.FoodLayout)
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
                    val index = foods.indexOfFirst { food -> food.name == button.text }
                    val food = foods.get(index)
                    selectedFood = food
                    detailsView.text = food.printDishInfo()
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
            foodLayout.addView(button, params)
        }
        setButtonsForFoods(foods, 0)

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
            (activity as? MainActivityInterface)?.foodsToCreateFoodButton()
        }

        previousButton.setOnClickListener {
            val filteredActivities = findFoods(filter)
            if(pageNumber > 0)
                pageNumber -= 1
            setButtonsForFoods(filteredActivities,pageNumber)
        }

        nextButton.setOnClickListener {
            val filteredActivities = findFoods(filter)
            if(pageNumber < filteredActivities.size/6)
                pageNumber += 1
            setButtonsForFoods(filteredActivities,pageNumber)
        }

        addButton.setOnClickListener {
            var amount = amountSelector.text.toString()
            Toast.makeText(requireContext(),"added $amount of product", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    fun findFoods(name: String): ArrayList<Food>
    {
        if(name.isBlank())
            return foods
        val foundFoods: ArrayList<Food> = arrayListOf<Food>()
        for(food in foods)
        {
            if(food.name.startsWith(name, true))
                foundFoods.add(food)
        }
        return foundFoods
    }

    fun setButtonsForFoods(foods: ArrayList<Food>, pageNumber: Int)
    {
        for(i in 0..5)
        {
            var button = buttons[i]
            button.setVisibility(View.VISIBLE)
            try {
                button.text = foods[pageNumber*6 + i].name
            }
            catch(_: Exception)
            {
                button.setVisibility(View.INVISIBLE)
            }
        }
    }

    private fun createTestFood(){
        for(i in 0..10)
        {
            val name = "test$i"
            val food = Food(name, listOf(Pair(Product("product1",12f),1f),Pair(Product("product2",24f),0.5f)))
            foods.add(food)
        }
    }
}