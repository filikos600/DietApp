package com.example.dietapp

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dietapp.Main.MainActivityInterface
import com.example.dietapp.Main.MainActivityModel
import com.example.dietapp.backend.User

class DishesScreen : Fragment() {

    private lateinit var addProduct: Button
    private lateinit var addFood: Button
    private lateinit var daySummary: TextView

    private lateinit var mainActivityModel: MainActivityModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.dishes_screen, container, false)

        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)

        addProduct = view.findViewById(R.id.addProduct)
        addFood = view.findViewById(R.id.addFood)
        daySummary = view.findViewById(R.id.daySummary)

        var products_eaten = "Add consumed products using button below\n"
        var foods_eaten = "Add consumed foods using button below\n"
        if (mainActivityModel.user.printEatenProducts().isNotBlank())
            products_eaten = "PRODUCTS:\n" + mainActivityModel.user.printEatenProducts() + "\n"
        if (mainActivityModel.user.printEatenFoods().isNotBlank())
            products_eaten = "FOODS:\n" + mainActivityModel.user.printEatenFoods() + "\n"

        daySummary.text = products_eaten + foods_eaten
        daySummary.movementMethod = ScrollingMovementMethod()

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as? MainActivityInterface)?.backToMainButton()
            }
        })

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