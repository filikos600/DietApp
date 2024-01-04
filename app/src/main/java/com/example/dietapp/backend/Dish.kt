package com.example.dietapp.backend

data class Dish (
    var name : String,
    var list_of_product: List<Pair<Product,Float>>
){
    fun print_clo(dish: Dish): String {
        return dish.name
    }

    fun print_calories(): Int{
        var a = 0
        for ((product, portion) in list_of_product){
            a += (product.calories * portion).toInt()
        }
        return a
    }


}
