package com.example.dietapp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dietapp.backend.User

class MainScreen : Fragment() {

    private lateinit var addDish: Button
    private lateinit var addActivity: Button
    private lateinit var summary: TextView
    private lateinit var user: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.main_screen, container, false)

        addDish = view.findViewById(R.id.addDish)
        addActivity = view.findViewById(R.id.addActivity)
        summary = view.findViewById(R.id.summary)

        addDish.setOnClickListener {
            val fragment = DishesScreen()
            val fragmentManager = activity?.supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.mainScreen, fragment)?.addToBackStack(null)?.commit()
        }

        addActivity.setOnClickListener {
            val fragment = AddActivityScreen()
            val fragmentManager = activity?.supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.mainScreen, fragment)?.addToBackStack(null)?.commit()
        }

    return view
    }

    fun getUserData()
    {
        //TODO read data about user from some file
    }
}