package com.example.dietapp.com.example.dietapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dietapp.R


class SettingsScreen : AppCompatActivity(){
    private lateinit var setting_1: Switch
    private lateinit var setting_2: CheckBox
    private lateinit var setting_3: Button
    private lateinit var setting_4: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.settings_screen)

        setting_1 = findViewById(R.id.switch_1)
        setting_2 = findViewById(R.id.checkbox_1)
        setting_3 = findViewById(R.id.button_1)
        setting_4 = findViewById(R.id.radiogroup)

        setting_1.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(applicationContext, "switched $isChecked", Toast.LENGTH_SHORT).show()
        }

        setting_2.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(applicationContext, "Checked $isChecked", Toast.LENGTH_SHORT).show()
        }

        setting_3.setOnClickListener {
            Toast.makeText(applicationContext,"hello", Toast.LENGTH_SHORT).show()
        }

        setting_4.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = findViewById<RadioButton>(checkedId)
            Toast.makeText(applicationContext,"radio " + radioButton.text.toString(), Toast.LENGTH_SHORT).show()
        }

    }
}