package com.example.dietapp.backend

import java.io.Serializable

data class Food(var name: String, var list_of_product: List<Pair<Product,Float>>): Serializable{
    fun getDishCalories(): Float{
        var a = 0f
        for ((product, portion) in list_of_product){
            a += (product.calories * portion)
        }
        return a
    }

    fun getDishFats(): Float{
        var a = 0f
        for ((product, portion) in list_of_product){
            a += (product.fats * portion)
        }
        return a
    }

    fun getDishCarbohydrates(): Float{
        var a = 0f
        for ((product, portion) in list_of_product){
            a += (product.carbohydrates * portion)
        }
        return a
    }

    fun getDishSugar(): Float{
        var a = 0f
        for ((product, portion) in list_of_product){
            a += (product.sugar * portion)
        }
        return a
    }

    fun getDishProtein(): Float{
        var a = 0f
        for ((product, portion) in list_of_product){
            a += (product.protein * portion)
        }
        return a
    }

    fun getDishSalt(): Float{
        var a = 0f
        for ((product, portion) in list_of_product){
            a += (product.salt * portion)
        }
        return a
    }

    fun printDishInfo(): String
    {
        var text = ""

        text += "$name\n"
        text += "Calories: ${getDishCalories()} kcal\n"
        text += "Carbohydrates: ${getDishCarbohydrates()} g\n"
        text += "Suagr: ${getDishSugar()} g\n"
        text += "Fats: ${getDishFats()} g\n"
        text += "Proteins: ${getDishProtein()} g\n"
        text += "Salt: ${getDishSalt()} g\n"

        return text
    }

}
