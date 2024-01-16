package com.example.dietapp.backend

data class Product (
    var name: String,
    var calories: Float,
    var fats: Float = 0f,
    var carbohydrates: Float = 0f,
    var sugar: Float = 0f,
    var protein: Float = 0f,
    var salt: Float = 0f,
    var portion: Float = 0f
)
{
    fun printProductInfo(): String{
        var text = ""
        text += "$name\n"
        text += "Calories: ${calories} kcal\n"
        text += "Carbohydrates: ${carbohydrates} g\n"
        text += "Suagr: ${sugar} g\n"
        text += "Fats: ${fats} g\n"
        text += "Proteins: ${protein} g\n"
        text += "Salt: ${salt} g\n"
        return text
    }
}