package com.example.dietapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dietapp.Main.MainActivityInterface
import com.example.dietapp.Main.MainActivityModel
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider


class SettingsScreen : Fragment(){

    private lateinit var resetButton: Button
    private lateinit var mainActivityModel: MainActivityModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        container?.removeAllViews()

        val view = inflater.inflate(R.layout.settings_screen, container, false)

        resetButton = view.findViewById(R.id.resetButton)
        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)

        val slider: Slider = view.findViewById(R.id.slider)
        val sliderValue: TextView = view.findViewById(R.id.sliderValue)

        sliderValue.text = mainActivityModel.kcalDailyGoal.toString()
        slider.value = mainActivityModel.kcalDailyGoal.toFloat()

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as? MainActivityInterface)?.backToMainButton()
            }
        })

        resetButton.setOnClickListener {
            mainActivityModel.activities = mainActivityModel.initializeActivitiesList()
            mainActivityModel.products = mainActivityModel.initializeProductList()
            mainActivityModel.foods = mainActivityModel.initializeFoodsList()
        }

        slider.addOnChangeListener{ _, value, _ ->
            var goal = value.toInt()
            mainActivityModel.kcalDailyGoal = goal
            (activity as? MainActivityInterface)?.updateSummaryBar()
            sliderValue.text = goal.toString()
        }

        return view
    }
}