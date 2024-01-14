package com.example.dietapp.backend
import java.time.LocalDate

class User() {

    var productList: ArrayList<Triple<Int, Product, LocalDate>> = arrayListOf<Triple<Int, Product, LocalDate>>()
    var dishList: ArrayList<Triple<Int, Dish, LocalDate>> = arrayListOf<Triple<Int, Dish, LocalDate>>()
    var activityList: ArrayList<Triple<Int, Activity, LocalDate>> = arrayListOf<Triple<Int, Activity, LocalDate>>()

    public fun AddActivity(_exerciseTime: Int, _activity: Activity)
    {
        val triple = Triple(_exerciseTime, _activity, LocalDate.now())
        activityList.add(triple)
    }

    public fun AddProduct(_portions: Int, _product: Product)
    {
        val triple = Triple(_portions, _product, LocalDate.now())
        productList.add(triple)
    }

    public fun AddDish(_portions: Int, _dish: Dish)
    {
        val triple = Triple(_portions, _dish, LocalDate.now())
        dishList.add(triple)
    }

    public fun getKcalBalance(_dayRange: Long = 1): Float
    {
        val minimalDate = LocalDate.now().minusDays(_dayRange - 1)
        var kcal = 0f

        for(product in productList)
            if(minimalDate <= product.third)
                kcal += product.first * product.second.calories

        for(dish in dishList)
            if(minimalDate <= dish.third)
                kcal += dish.first * dish.second.getDishCalories()

        for(activity in activityList)
            if(minimalDate <= activity.third)
                kcal -= activity.first * activity.second.kcalBurnt

        return kcal
    }

    public fun getCarbohydatesIntake(_dayRange: Long = 1): Float
    {
        val minimalDate = LocalDate.now().minusDays(_dayRange - 1)
        var carbohydrates = 0f

        for(product in productList)
            if(minimalDate <= product.third)
                carbohydrates += product.first * product.second.carbohydrates

        for(dish in dishList)
            if(minimalDate <= dish.third)
                carbohydrates += dish.first * dish.second.getDishCarbohydrates()

        return carbohydrates
    }

    public fun getFatsIntake(_dayRange: Long = 1): Float
    {
        val minimalDate = LocalDate.now().minusDays(_dayRange - 1)
        var fats = 0f

        for(product in productList)
            if(minimalDate <= product.third)
                fats += product.first * product.second.fats

        for(dish in dishList)
            if(minimalDate <= dish.third)
                fats += dish.first * dish.second.getDishFats()

        return fats
    }

    public fun getSugarIntake(_dayRange: Long = 1): Float
    {
        val minimalDate = LocalDate.now().minusDays(_dayRange - 1)
        var sugar = 0f

        for(product in productList)
            if(minimalDate <= product.third)
                sugar += product.first * product.second.sugar

        for(dish in dishList)
            if(minimalDate <= dish.third)
                sugar += dish.first * dish.second.getDishSugar()

        return sugar
    }

    public fun getProteinIntake(_dayRange: Long = 1): Float
    {
        val minimalDate = LocalDate.now().minusDays(_dayRange - 1)
        var protein = 0f

        for(product in productList)
            if(minimalDate <= product.third)
                protein += product.first * product.second.protein

        for(dish in dishList)
            if(minimalDate <= dish.third)
                protein += dish.first * dish.second.getDishProtein()

        return protein
    }

    public fun getSaltIntake(_dayRange: Long = 1): Float
    {
        val minimalDate = LocalDate.now().minusDays(_dayRange - 1)
        var salt = 0f

        for(product in productList)
            if(minimalDate <= product.third)
                salt += product.first * product.second.salt

        for(dish in dishList)
            if(minimalDate <= dish.third)
                salt += dish.first * dish.second.getDishSalt()

        return salt
    }

    public fun getUserInfo(_dayRange: Long): String
    {
        var text = ""
        text += "Calories balance: ${getKcalBalance(_dayRange)} kcal\n"
        text += "Carbohydrates: ${getCarbohydatesIntake(_dayRange)} g\n"
        text += "Sugar: ${getSugarIntake(_dayRange)} g\n"
        text += "Fats: ${getFatsIntake(_dayRange)} g\n"
        text += "Proteins: ${getProteinIntake(_dayRange)} g\n"
        text += "Salt: ${getSaltIntake(_dayRange)} g\n"
        return text
    }
}