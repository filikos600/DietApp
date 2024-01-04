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

    public fun GetKcalBalance(_dayRange: Long = 1): Float
    {
        val minimalDate = LocalDate.now().minusDays(_dayRange - 1)
        var kcal = 0f

        for(product in productList)
            if(minimalDate <= product.third)
                kcal += product.first * product.second.calories

        for(dish in dishList)
        {
            if(minimalDate <= dish.third)
                for(product in dish.second.list_of_product)
                    kcal += dish.first * product.first.calories
        }

        for(activity in activityList)
            if(minimalDate <= activity.third)
                kcal -= activity.first * activity.second.kcalBurnt

        return kcal
    }

    public fun GetCarbohydatesIntake(_dayRange: Long = 1): Float
    {
        val minimalDate = LocalDate.now().minusDays(_dayRange - 1)
        var carbohydrates = 0f

        for(product in productList)
            if(minimalDate <= product.third)
                carbohydrates += product.first * product.second.carbohydrates

        for(dish in dishList)
        {
            if(minimalDate <= dish.third)
                for(product in dish.second.list_of_product)
                    carbohydrates += dish.first * product.first.carbohydrates
        }

        return carbohydrates
    }

    public fun GetFatsIntake(_dayRange: Long = 1): Float
    {
        val minimalDate = LocalDate.now().minusDays(_dayRange - 1)
        var fats = 0f

        for(product in productList)
            if(minimalDate <= product.third)
                fats += product.first * product.second.fats

        for(dish in dishList)
        {
            if(minimalDate <= dish.third)
                for(product in dish.second.list_of_product)
                    fats += dish.first * product.first.fats
        }

        return fats
    }

    public fun GetSugarIntake(_dayRange: Long = 1): Float
    {
        val minimalDate = LocalDate.now().minusDays(_dayRange - 1)
        var sugar = 0f

        for(product in productList)
            if(minimalDate <= product.third)
                sugar += product.first * product.second.sugar

        for(dish in dishList)
        {
            if(minimalDate <= dish.third)
                for(product in dish.second.list_of_product)
                    sugar += dish.first * product.first.sugar
        }

        return sugar
    }

    public fun GetProteinIntake(_dayRange: Long = 1): Float
    {
        val minimalDate = LocalDate.now().minusDays(_dayRange - 1)
        var protein = 0f

        for(product in productList)
            if(minimalDate <= product.third)
                protein += product.first * product.second.protein

        for(dish in dishList)
        {
            if(minimalDate <= dish.third)
                for(product in dish.second.list_of_product)
                    protein += dish.first * product.first.protein
        }

        return protein
    }

    public fun GetSaltIntake(_dayRange: Long = 1): Float
    {
        val minimalDate = LocalDate.now().minusDays(_dayRange - 1)
        var salt = 0f

        for(product in productList)
            if(minimalDate <= product.third)
                salt += product.first * product.second.salt

        for(dish in dishList)
        {
            if(minimalDate <= dish.third)
                for(product in dish.second.list_of_product)
                    salt += dish.first * product.first.salt
        }

        return salt
    }
}