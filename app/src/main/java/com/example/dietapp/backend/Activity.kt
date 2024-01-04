package com.example.dietapp.backend

data class Activity(val _name: String, val _desc: String, val _kcalburnt: Float) {
    var name: String = _name
    var desc: String = _desc
    var kcalBurnt: Float = _kcalburnt
}