package com.example.dietapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.internal.ContextUtils.getActivity

class NewActivityScreen : Fragment() {

    private lateinit var addActivity: Button
    private lateinit var cancel: Button
    private lateinit var name: EditText
    private lateinit var desc: EditText
    private lateinit var kcalReduction: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.new_activity_screen, container, false)

        addActivity = view.findViewById(R.id.add)
        cancel = view.findViewById(R.id.cancel)
        name = view.findViewById(R.id.activityName)
        desc = view.findViewById(R.id.activityDesc)
        kcalReduction = view.findViewById(R.id.kcalReduction)

        addActivity.setOnClickListener {
            var result = "name: " + name.text.toString() + " desc: " + desc.text.toString() + " kcal usage: " + kcalReduction.text.toString()
            Toast.makeText(requireContext(),result, Toast.LENGTH_SHORT).show()
        }

        cancel.setOnClickListener {
            Toast.makeText(requireContext(),"cancel is pressed", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}