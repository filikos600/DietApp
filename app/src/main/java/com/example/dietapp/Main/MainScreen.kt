package com.example.dietapp.Main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dietapp.R
import com.example.dietapp.backend.User
import com.google.gson.Gson

class MainScreen : Fragment() {

    private lateinit var addDish: Button
    private lateinit var addActivity: Button
    private lateinit var summary: TextView

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.main_screen, container, false)

        addDish = view.findViewById(R.id.addDish)
        addActivity = view.findViewById(R.id.addActivity)
        summary = view.findViewById(R.id.summary)



        addDish.setOnClickListener {
            (activity as? MainActivityInterface)?.mainToAddDishButton()
        }

        addActivity.setOnClickListener {
            (activity as? MainActivityInterface)?.mainToActivityButton()
        }

        CacheTest()

    return view
    }

    private fun CacheTest(){
        sharedPreferences =  requireActivity().getPreferences(Context.MODE_PRIVATE)

        var count = sharedPreferences.getInt("app_open_count", 0)
        summary.text = "App opened: $count times"
        count++

        with(sharedPreferences.edit()) {
            putInt("app_open_count", count)
            apply()
        }
    }

    fun getUserData()
    {
        //TODO read data about user from some file
    }

}