package com.example.dietapp.backend
import java.io.Serializable
import java.time.LocalDate

class User(
    var productList: MutableList<Triple<Float, Product, LocalDate>> = mutableListOf<Triple<Float, Product, LocalDate>>(),
    var foodList: MutableList<Triple<Float, Food, LocalDate>> = mutableListOf<Triple<Float, Food, LocalDate>>(),
    var activityList: MutableList<Triple<Int, Activity, LocalDate>> = mutableListOf<Triple<Int, Activity, LocalDate>>()
) : Serializable {

    public fun AddActivity(_exerciseTime: Int, _activity: Activity)
    {
        val triple = Triple(_exerciseTime, _activity, LocalDate.now())
        activityList.add(triple)
    }

    public fun AddProduct(_portions: Float, _product: Product)
    {
        val triple = Triple(_portions, _product, LocalDate.now())
        productList.add(triple)
    }

    public fun AddFood(_portions: Float, _food: Food)
    {
        val triple = Triple(_portions, _food, LocalDate.now())
        foodList.add(triple)
    }

    public fun getKcalBalance(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): Float
    {
        val minimalDate = _refrenceDay.minusDays(_Range - 1)
        var kcal = 0f

        for(product in productList)
            if(minimalDate <= product.third && _refrenceDay >= product.third)
                kcal += product.first * product.second.calories

        for(dish in foodList)
            if(minimalDate <= dish.third && _refrenceDay >= dish.third)
                kcal += dish.first * dish.second.getDishCalories()

        for(activity in activityList)
            if(minimalDate <= activity.third && _refrenceDay >= activity.third)
                kcal -= activity.first * activity.second.kcalBurnt

        return kcal
    }

    public fun getCarbohydatesIntake(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): Float
    {
        val minimalDate = _refrenceDay.minusDays(_Range - 1)
        var carbohydrates = 0f

        for(product in productList)
            if(minimalDate <= product.third && _refrenceDay >= product.third )
                carbohydrates += product.first * product.second.carbohydrates

        for(dish in foodList)
            if(minimalDate <= dish.third && _refrenceDay >= dish.third)
                carbohydrates += dish.first * dish.second.getDishCarbohydrates()

        return carbohydrates
    }

    public fun getFatsIntake(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): Float
    {
        val minimalDate = _refrenceDay.minusDays(_Range - 1)
        var fats = 0f

        for(product in productList)
            if(minimalDate <= product.third && _refrenceDay >= product.third )
                fats += product.first * product.second.fats

        for(dish in foodList)
            if(minimalDate <= dish.third && _refrenceDay >= dish.third)
                fats += dish.first * dish.second.getDishFats()

        return fats
    }

    public fun getSugarIntake(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): Float
    {
        val minimalDate = _refrenceDay.minusDays(_Range - 1)
        var sugar = 0f

        for(product in productList)
            if(minimalDate <= product.third && _refrenceDay >= product.third)
                sugar += product.first * product.second.sugar

        for(dish in foodList)
            if(minimalDate <= dish.third && _refrenceDay >= dish.third)
                sugar += dish.first * dish.second.getDishSugar()

        return sugar
    }

    public fun getProteinIntake(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): Float
    {
        val minimalDate = _refrenceDay.minusDays(_Range - 1)
        var protein = 0f

        for(product in productList)
            if(minimalDate <= product.third && _refrenceDay >= product.third)
                protein += product.first * product.second.protein

        for(dish in foodList)
            if(minimalDate <= dish.third  && _refrenceDay >= dish.third)
                protein += dish.first * dish.second.getDishProtein()

        return protein
    }

    public fun getSaltIntake(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): Float
    {
        val minimalDate = _refrenceDay.minusDays(_Range - 1)
        var salt = 0f

        for(product in productList)
            if(minimalDate <= product.third && _refrenceDay >= product.third)
                salt += product.first * product.second.salt

        for(dish in foodList)
            if(minimalDate <= dish.third && _refrenceDay >= dish.third)
                salt += dish.first * dish.second.getDishSalt()

        return salt
    }
    fun getUserInfo(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): String
    {
        var text = ""
        text += "Calories balance: ${getKcalBalance(_refrenceDay, _Range)} kcal\n"
        text += "Carbohydrates: ${getCarbohydatesIntake(_refrenceDay, _Range)} g\n"
        text += "Sugar: ${getSugarIntake(_refrenceDay, _Range)} g\n"
        text += "Fats: ${getFatsIntake(_refrenceDay, _Range)} g\n"
        text += "Proteins: ${getProteinIntake(_refrenceDay, _Range)} g\n"
        text += "Salt: ${getSaltIntake(_refrenceDay, _Range)} g\n"
        return text
    }
}