package com.example.dietapp.backend
import java.io.Serializable
import java.time.LocalDate

class User(
    var productList: MutableList<Triple<Float, Product, LocalDate>> = mutableListOf<Triple<Float, Product, LocalDate>>(),
    var foodList: MutableList<Triple<Float, Food, LocalDate>> = mutableListOf<Triple<Float, Food, LocalDate>>(),
    var activityList: MutableList<Triple<Int, Activity, LocalDate>> = mutableListOf<Triple<Int, Activity, LocalDate>>()
) : Serializable {

    fun AddActivity(_exerciseTime: Int, _activity: Activity)
    {
        val triple = Triple(_exerciseTime, _activity, LocalDate.now())
        activityList.add(triple)
    }

    fun AddProduct(_portions: Float, _product: Product)
    {
        val triple = Triple(_portions, _product, LocalDate.now())
        productList.add(triple)
    }

    fun AddFood(_portions: Float, _food: Food)
    {
        val triple = Triple(_portions, _food, LocalDate.now())
        foodList.add(triple)
    }

    fun getKcalBalance(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): Float
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

    fun getCarbohydatesIntake(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): Float
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

    fun getFatsIntake(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): Float
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

    fun getSugarIntake(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): Float
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

    fun getProteinIntake(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): Float
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

    fun getSaltIntake(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): Float
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

    fun printEatenProducts(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): String
    {
        val minimalDate = _refrenceDay.minusDays(_Range - 1)
        var text = ""
        for(product in productList)
            if(minimalDate <= product.third && _refrenceDay >= product.third)
                text += product.second.name + " " + product.first + " portions \n"
        return text
    }

    fun printEatenFoods(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): String
    {
        val minimalDate = _refrenceDay.minusDays(_Range - 1)
        var text = ""
        for(food in foodList)
            if(minimalDate <= food.third && _refrenceDay >= food.third)
                text += food.second.name + " " + food.first + " portions \n"
        return text
    }

    fun printDoneActivities(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): String
    {
        val minimalDate = _refrenceDay.minusDays(_Range - 1)
        var text = ""
        for(activity in activityList)
            if(minimalDate <= activity.third && _refrenceDay >= activity.third)
                text += activity.second.name + " " + activity.first + " cycles \n"
        return text
    }

    fun printAllUserDetails(_refrenceDay: LocalDate = LocalDate.now(), _Range: Long = 1): String
    {
        var text = ""
        text += getUserInfo(_refrenceDay,_Range) + "\n"
        text += "PRODUCTS:\n" + printEatenProducts(_refrenceDay,_Range) + "\n"
        text += "FOODS:\n" + printEatenFoods(_refrenceDay,_Range) + "\n"
        text += "ACTIVITIES:\n" + printDoneActivities(_refrenceDay,_Range)
        return text
    }

}