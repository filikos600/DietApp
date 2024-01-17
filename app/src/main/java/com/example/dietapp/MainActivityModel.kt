package com.example.dietapp

import androidx.lifecycle.ViewModel
import com.example.dietapp.backend.Activity
import com.example.dietapp.backend.Food
import com.example.dietapp.backend.Product
import com.example.dietapp.backend.User


class MainActivityModel : ViewModel() {
    val products: MutableList<Product> = arrayListOf<Product>()
    val foods: MutableList<Food> = arrayListOf<Food>()
    val activities: MutableList<Activity> = arrayListOf<Activity>()
    val user: User = User()

    val productsUsedInDish: MutableList<Pair<Product,Float>> = arrayListOf<Pair<Product,Float>>()

    // w sumie na inicie możnaby zczytywać te listy z cache i zrobić jakąś funkcję przy zamykaniu żeby zapisywał zmiany ale to potem
    init {
        products.add(Product("egg", 20f))
        products.add(Product("egg?", 15f))
        products.add(Product("egg!", 12f))
        products.add(Product("EGGGG", 42f, 12f, 6f, 4f, 3f, 1f, 2f))


        for(i in 0..8)
        {
            val name = "test$i"
            val food = Food(name, listOf(Pair(products[0],1f),Pair(products[1],0.5f)))
            foods.add(food)
        }
    }
}