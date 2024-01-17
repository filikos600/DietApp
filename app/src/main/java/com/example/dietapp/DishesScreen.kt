package com.example.dietapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dietapp.backend.User

class DishesScreen : Fragment() {

    private lateinit var addProduct: Button
    private lateinit var addFood: Button
    private lateinit var daySummary: TextView
    private lateinit var user: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.dishes_screen, container, false)

        addProduct = view.findViewById(R.id.addProduct)
        addFood = view.findViewById(R.id.addFood)
        daySummary = view.findViewById(R.id.daySummary)

        //daySummary.text = printUserInfo()

        addProduct.setOnClickListener {
            (activity as? MainActivityInterface)?.dishesToProductButton()
        }

        addFood.setOnClickListener {
            (activity as? MainActivityInterface)?.dishesToFoodButton()
        }
    return view
    }

    //fun printUserInfo(): String
    //{
    //    return user.getUserInfo(1)
    //}

}