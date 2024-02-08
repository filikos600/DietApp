package com.example.dietapp.Main

import androidx.lifecycle.ViewModel
import com.example.dietapp.backend.Activity
import com.example.dietapp.backend.Food
import com.example.dietapp.backend.Product
import com.example.dietapp.backend.User


class MainActivityModel : ViewModel() {
    var products: MutableList<Product> = mutableListOf<Product>()
    var foods: MutableList<Food> = mutableListOf<Food>()
    var activities: MutableList<Activity> = mutableListOf<Activity>()
    var user: User = User()
    var kcalDailyGoal = 2000

    val productsUsedInDish: MutableList<Pair<Product,Float>> = mutableListOf<Pair<Product,Float>>()

    var editedProductIndex = -1
    var editedFoodIndex = -1
    var editedActivityIndex = -1

    fun initializeProductList(): MutableList<Product> {
        var products = mutableListOf<Product>()
        products.add(Product("Apple", 52f, 0.2f, 14f, 10f, 0.3f, 0f, 182f))
        products.add(Product("Banana", 89f, 0.3f, 23f, 12f, 1.1f, 0f, 118f))
        products.add(Product("Broccoli", 34f, 0.4f, 7f, 1f, 2.8f, 0f, 91f))
        products.add(Product("Chicken Breast", 165f, 3.6f, 0f, 0f, 31f, 0f, 100f))
        products.add(Product("Eggs", 155f, 11f, 1.1f, 0.7f, 13f, 0.33f, 50f))
        products.add(Product("Salmon", 208f, 13f, 0f, 0f, 20.4f, 0f, 100f))
        products.add(Product("Brown Rice", 112f, 0.9f, 23f, 0.4f, 2.6f, 0f, 100f))
        products.add(Product("Olive Oil", 884f, 100f, 0f, 0f, 0f, 0f, 15f))
        products.add(Product("Spinach", 23f, 0.4f, 3.6f, 0.4f, 2.9f, 0f, 30f))
        products.add(Product("Milk", 42f, 1f, 5f, 5f, 3.4f, 0.1f, 244f))
        products.add(Product("Cheese", 402f, 33f, 1.3f, 0.1f, 25f, 1.6f, 28f))
        products.add(Product("Potatoes", 77f, 0.1f, 17f, 0.8f, 2f, 0f, 100f))
        products.add(Product("Almonds", 579f, 50f, 22f, 4.8f, 21f, 0.01f, 28f))
        products.add(Product("Avocado", 160f, 15f, 9f, 0.7f, 2f, 7f, 201f))
        products.add(Product("Wheat Bread", 247f, 3.1f, 48f, 5.2f, 8.8f, 0.53f, 32f))
        products.add(Product("Peanut Butter", 588f, 50f, 20f, 6f, 25f, 667f, 32f))
        products.add(Product("Yogurt", 59f, 1.5f, 4.7f, 4.7f, 4.1f, 0.1f, 150f))
        products.add(Product("Tofu", 76f, 4.8f, 1.9f, 0.7f, 8.1f, 7f, 100f))
        products.add(Product("Quinoa", 120f, 2f, 21f, 0.9f, 4f, 7f, 185f))
        products.add(Product("Tomato", 18f, 0.2f, 3.9f, 2.6f, 0.9f, 5f, 123f))
        products.add(Product("Beef", 250f, 20f, 0f, 0f, 25f, 30f, 100f))
        products.add(Product("Orange", 47f, 0.1f, 12f, 9.4f, 0.9f, 0f, 131f))
        products.add(Product("Grapes", 69f, 0.2f, 18f, 15f, 0.6f, 0.02f, 92f))
        products.add(Product("Carrots", 41f, 0.2f, 10f, 4.7f, 0.9f, 0.7f, 61f))
        products.add(Product("Pork", 242f, 16f, 0f, 0f, 23f, 0.6f, 100f))
        products.add(Product("Lentils", 116f, 0.4f, 20f, 2f, 9f, 0.02f, 100f))
        products.add(Product("Turkey", 189f, 8f, 0f, 0f, 29f, 1f, 100f))
        products.add(Product("Cauliflower", 25f, 0.3f, 5f, 2f, 2f, 0.3f, 100f))
        products.add(Product("Honey", 304f, 0f, 82f, 82f, 0.3f, 0.04f, 21f))
        products.add(Product("Walnuts", 654f, 65f, 14f, 3f, 15f, 0.01f, 28f))
        products.add(Product("Blueberries", 57f, 0.3f, 14f, 10f, 0.7f, 0.01f, 68f))
        products.add(Product("Shrimp", 99f, 0.3f, 0f, 0f, 24f, 0.12f, 100f))
        products.add(Product("Asparagus", 20f, 0.1f, 3.9f, 1.9f, 2.2f, 0.02f, 68f))
        products.add(Product("Coconut Oil", 862f, 100f, 0f, 0f, 0f, 0.02f, 15f))
        products.add(Product("Chickpeas", 164f, 2.6f, 27f, 4.8f, 8.9f, 0.2f, 164f))
        products.add(Product("Pineapple", 50f, 0.1f, 13f, 9.9f, 0.5f, 0.01f, 112f))
        products.add(Product("Soy Milk", 54f, 1.8f, 3.3f, 1.3f, 3.3f, 0.4f, 244f))
        products.add(Product("Cottage Cheese", 98f, 4.3f, 3.4f, 3.4f, 11f, 3.6f, 113f))
        products.add(Product("Cucumber", 15f, 0.1f, 3.6f, 1.7f, 0.6f, 0.02f, 301f))
        products.add(Product("Raspberries", 52f, 0.7f, 12f, 4f, 1.5f, 0.01f, 123f))
        products.add(Product("Lettuce", 15f, 0.2f, 2.9f, 1.2f, 1.4f, 0.3f, 60f))
        products.add(Product("Sardines", 208f, 11f, 0f, 0f, 25f, 4.8f, 92f))
        products.add(Product("Kiwi", 61f, 0.5f, 15f, 9.1f, 1.1f, 0.03f, 76f))
        products.add(Product("Feta Cheese", 264f, 21f, 4f, 1f, 14f, 1.2f, 100f))
        products.add(Product("Pumpkin Seeds", 559f, 49f, 11f, 1.4f, 30f, 0.2f, 28f))
        products.add(Product("Hummus", 177f, 9f, 18f, 1.4f, 5.5f, 2.6f, 28f))
        products.add(Product("Artichokes", 47f, 0.2f, 11f, 0.9f, 3.3f, 0.1f, 120f))
        products.add(Product("Black Beans", 339f, 1.8f, 62f, 0.3f, 21f, 0.02f, 172f))
        products.add(Product("Oats", 389f, 6.9f, 66f, 0.9f, 16.9f, 0.02f, 81f))
        products.add(Product("Dates", 282f, 0.4f, 75f, 63f, 2.5f, 0.02f, 24f))
        products.add(Product("Red Bell Pepper", 31f, 0.3f, 6f, 4.2f, 1.3f, 0.04f, 119f))
        products.add(Product("Grapeseed Oil", 884f, 100f, 0f, 0f, 0f, 0f, 15f))
        products.add(Product("Onion", 40f, 0.1f, 9.3f, 4.7f, 1.1f, 4f, 150f))
        products.add(Product("Pasta", 131f, 1.1f, 25f, 0.7f, 5.5f, 0f, 120f))
        products.add(Product("Lemon", 29f, 0.3f, 9.3f, 2.8f, 1.1f, 0.02f, 100f))
        products.add(Product("Garlic", 149f, 0.5f, 33f, 1f, 6.3f, 0.02f, 60f))
        products.add(Product("Beef", 250f, 20f, 0f, 0f, 25f, 30f, 100f))
        return products
    }

    fun initializeFoodsList(): MutableList<Food> {
        var foods = mutableListOf<Food>()

        val chickenBreast = products.find{it.name == "Chicken Breast"}
        val rice = products.find{it.name == "Rice"}
        val tomato = products.find{it.name == "Tomato"}
        val onion = products.find{it.name == "Onion"}
        val oliveOil = products.find{it.name == "Olive Oil"}
        val garlic = products.find{it.name == "Garlic"}
        val pasta = products.find{it.name == "Pasta"}
        val beef = products.find{it.name == "Beef"}
        val lettuce = products.find{it.name == "Lettuce"}
        val lemon = products.find{it.name == "Lemon"}
        val cucumber = products.find{it.name == "Cucumber"}
        val fetaCheese = products.find{it.name == "Feta Cheese"}

        if (chickenBreast != null && rice != null && tomato != null && onion != null && oliveOil != null )
            foods.add(Food("Chicken and Rice", mutableListOf(Pair(chickenBreast, 2f), Pair(rice, 1f), Pair(tomato, 0.5f), Pair(onion, 0.3f), Pair(oliveOil, 0.2f))))
        if (pasta != null && garlic != null && oliveOil != null )
            foods.add(Food("Pasta with Garlic and Olive Oil", mutableListOf(Pair(pasta, 1f), Pair(garlic, 0.2f), Pair(oliveOil, 0.3f))))
        if (beef != null && lettuce != null && tomato != null && onion != null && oliveOil != null && lemon != null)
            foods.add(Food("Beef Salad", mutableListOf(Pair(beef, 1f), Pair(lettuce, 0.5f), Pair(tomato, 0.3f), Pair(onion, 0.2f), Pair(oliveOil, 0.1f), Pair(lemon, 0.1f))))
        if (cucumber != null && lettuce != null && tomato != null && onion != null && oliveOil != null && lemon != null)
            foods.add(Food("Cucumber Salad", mutableListOf(Pair(cucumber, 1f), Pair(lettuce, 0.5f), Pair(tomato, 0.3f), Pair(onion, 0.2f), Pair(oliveOil, 0.1f), Pair(lemon, 0.1f))))
        if (lettuce != null && tomato != null && cucumber != null && fetaCheese != null && oliveOil != null && lemon != null)
            foods.add(Food("Greek Salad", mutableListOf(Pair(lettuce, 0.5f), Pair(tomato, 0.3f), Pair(cucumber, 0.2f), Pair(fetaCheese, 0.2f), Pair(oliveOil, 0.1f), Pair(lemon, 0.1f))))
        if (beef != null && pasta != null && tomato != null && onion != null && garlic != null && oliveOil != null)
            foods.add(Food("Spaghetti Bolognese", mutableListOf(Pair(beef, 1f), Pair(pasta, 1.5f), Pair(tomato, 0.5f), Pair(onion, 0.3f), Pair(garlic, 0.1f), Pair(oliveOil, 0.1f))))
        return foods
    }

    fun initializeActivitiesList(): MutableList<Activity> {
        var activities = mutableListOf<Activity>()

        activities.add(Activity("slow jog", "30 minutes slow jog", 200f))
        activities.add(Activity("long slow jog", "2 hours of slow jog", 800f))
        activities.add(Activity("fast jog", "30 minutes fast jog", 450f))
        activities.add(Activity("marathon running", "running for 42km", 2600f))
        activities.add(Activity("cycling", "an hour long cycling session", 500f))
        activities.add(Activity("yoga", "an hour of fairly intense yoga", 300f))
        activities.add(Activity("skiing", "half a day of skiing", 1200f))

        return activities
    }
}