package com.example.dietapp.Main

import androidx.lifecycle.ViewModel
import com.example.dietapp.backend.Activity
import com.example.dietapp.backend.Food
import com.example.dietapp.backend.Product
import com.example.dietapp.backend.User


class MainActivityModel : ViewModel() {
    var products: MutableList<Product> = arrayListOf<Product>()
    var foods: MutableList<Food> = arrayListOf<Food>()
    var activities: MutableList<Activity> = arrayListOf<Activity>()
    var user: User = User()
    var kcalDailyGoal = 2000

    val productsUsedInDish: MutableList<Pair<Product,Float>> = arrayListOf<Pair<Product,Float>>()

    // w sumie na inicie możnaby zczytywać te listy z cache i zrobić jakąś funkcję przy zamykaniu żeby zapisywał zmiany ale to potem
    init {
        //createTestSamples()
    }
    fun initializeProductList(): MutableList<Product> {
        var products = mutableListOf<Product>()
        products.add(Product("egg", 20f))
        products.add(Product("egg?", 15f))
        products.add(Product("egg!", 12f))
        products.add(Product("EGGGG", 42f, 12f, 6f, 4f, 3f, 1f, 2f))
        return products
    }

    fun initializeFoodsList(): MutableList<Food> {
        var foods = mutableListOf<Food>()
        for(i in 0..8) {
            val name = "test$i"
            val food = Food(name, listOf(Pair(products[0],1f),Pair(products[1],0.5f)))
            foods.add(food)
        }
        return foods
    }

    fun initializeActivitiesList(): MutableList<Activity> {
        var activities = mutableListOf<Activity>()
        for(i in 0..10)
        {
            val name = "test$i"
            val activity = Activity(name, "test", 20f)
            activities.add(activity)
        }
        return activities
    }
}