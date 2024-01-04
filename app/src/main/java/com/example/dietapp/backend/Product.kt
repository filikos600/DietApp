package com.example.dietapp.backend

data class Product (
    var name: String,
    var calories: Float,
    var fats: Float = -1f,
    var carbohydrates: Float = -1f,
    var sugar: Float = -1f,
    var protein: Float = -1f,
    var salt: Float = -1f,
    var portion: Float = -1f
)