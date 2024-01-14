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
import android.widget.Toast
import androidx.fragment.app.Fragment


class SettingsScreen : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        container?.removeAllViews()

        val view = inflater.inflate(R.layout.settings_screen, container, false)

        val setting_1: Switch = view.findViewById(R.id.switch_1)
        val setting_2: CheckBox = view.findViewById(R.id.checkbox_1)
        val setting_3: Button = view.findViewById(R.id.button_1)
        val setting_4: RadioGroup = view.findViewById(R.id.radiogroup)

        setting_1.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(requireContext(), "switched $isChecked", Toast.LENGTH_SHORT).show()
        }

        setting_2.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(requireContext(), "Checked $isChecked", Toast.LENGTH_SHORT).show()
        }

        setting_3.setOnClickListener {
            Toast.makeText(requireContext(),"hello", Toast.LENGTH_SHORT).show()
        }

        setting_4.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = view.findViewById<RadioButton>(checkedId)
            Toast.makeText(requireContext(),"radio " + radioButton.text.toString(), Toast.LENGTH_SHORT).show()
        }
        return view
    }
}