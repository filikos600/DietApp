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

    private lateinit var addBasicIngredient: Button
    private lateinit var addDish: Button
    private lateinit var daySummary: TextView
    private lateinit var user: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.dishes_screen, container, false)

        addBasicIngredient = view.findViewById(R.id.addBasicIngredient)
        addDish = view.findViewById(R.id.addDish)
        daySummary = view.findViewById(R.id.daySummary)

        //daySummary.text = printUserInfo()

        addBasicIngredient.setOnClickListener {
            //TODO go to product screen
            val fragment = ProductsScreen()
            val fragmentManager = activity?.supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.dishesScreen, fragment)?.addToBackStack(null)?.commit()
        }

        addDish.setOnClickListener {
            //TODO go to food screen
            val fragment = ProductsScreen()
            val fragmentManager = activity?.supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.dishesScreen, fragment)?.addToBackStack(null)?.commit()
        }
    return view
    }

    //fun printUserInfo(): String
    //{
    //    return user.getUserInfo(1)
    //}

}