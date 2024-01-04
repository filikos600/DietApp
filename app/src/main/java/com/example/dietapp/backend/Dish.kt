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


fun main(){
    var product1 = Product("prod1", 100f)
    var product2 = Product("prod2", 200f)
    var my_dish = Dish("amciu", (listOf(Pair(product1, 1f), Pair(product2, 1f))))
    println(my_dish.print_calories())
}