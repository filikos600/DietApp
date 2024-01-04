package com.example.dietapp.backend

data class Product (
    var name: String,
    var calories: Float,
    var fats: Float = -1f,
    var carbohydrates: Float = -1f,
    var sugar: Float = -1f,
    var protein: Float = -1f,
    var salt: Float = -1f,
    var portion: Float = -1f
)

fun main(){
    val egg = Product("egg", 60f);
    println("Helo")
    println(egg.name)
    egg.calories = 80f
    println(egg)

    val kinder_chocolate = Product("kinder_chocolate", 566f,35f,53.5f,53.3f,8.7f,0.3f,12.5f)
    println("sugar in 2 portions: " + kinder_chocolate.sugar/100*kinder_chocolate.portion)
}