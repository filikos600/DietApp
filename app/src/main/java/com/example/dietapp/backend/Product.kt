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