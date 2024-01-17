package com.example.dietapp

import androidx.lifecycle.ViewModel
import com.example.dietapp.backend.Activity
import com.example.dietapp.backend.Food
import com.example.dietapp.backend.Product
import com.example.dietapp.backend.User


class MainActivityModel : ViewModel() {
    val products: ArrayList<Product> = arrayListOf<Product>()
    val foods: ArrayList<Food> = arrayListOf<Food>()
    val activities: ArrayList<Activity> = arrayListOf<Activity>()
    val user: User = User()

    // w sumie na inicie możnaby zczytywać te listy z cache i zrobić jakąś funkcję przy zamykaniu żeby zapisywał zmiany ale to potem
    init {
        products.add(Product("egg", 20f))
        products.add(Product("egg?", 15f))
        products.add(Product("egg!", 12f))
        products.add(Product("EGGGG", 42f, 12f, 6f, 4f, 3f, 1f, 2f))
    }
}