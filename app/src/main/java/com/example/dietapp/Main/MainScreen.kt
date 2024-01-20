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
import com.google.gson.reflect.TypeToken

class MainScreen : Fragment() {
    private val gson = Gson()

    private lateinit var addDish: Button
    private lateinit var addActivity: Button
    private lateinit var summary: TextView
    private lateinit var user: User

    private lateinit var mainActivityModel: MainActivityModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.main_screen, container, false)

        addDish = view.findViewById(R.id.addDish)
        addActivity = view.findViewById(R.id.addActivity)
        summary = view.findViewById(R.id.summary)

        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)

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


    private inline fun <reified T> loadObject(key: String, clazz: Class<T>): T? {
        val json = sharedPreferences.getString(key, null)
        return if (json != null) {
            gson.fromJson(json, clazz)
        } else {
            null
        }
    }

    private inline fun <reified T> loadList(key: String): MutableList<T> {
        val json = sharedPreferences.getString(key, null)
        return if (json != null) {
            val typeToken = TypeToken.getParameterized(MutableList::class.java, T::class.java).type
            gson.fromJson(json, typeToken)
        } else {
            mutableListOf()
        }
    }

    private fun saveObject(key: String, value: Any) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(value)
        editor.putString(key, json)
        editor.apply()
    }

    private fun saveList(key: String, list: List<Any>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun saveData() {
        saveList("products", mainActivityModel.products)
        saveList("foods", mainActivityModel.foods)
        saveList("activities", mainActivityModel.activities)
        saveObject("user", user)
    }
}