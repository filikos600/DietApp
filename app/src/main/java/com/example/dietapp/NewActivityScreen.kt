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
import com.example.dietapp.backend.Activity
import com.google.android.material.internal.ContextUtils.getActivity

class NewActivityScreen : Fragment() {

    private lateinit var addActivity: Button
    private lateinit var cancel: Button
    private lateinit var name: EditText
    private lateinit var desc: EditText
    private lateinit var kcalReduction: EditText

    private lateinit var newActivity: Activity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.new_activity_screen, container, false)

        addActivity = view.findViewById(R.id.add)
        cancel = view.findViewById(R.id.cancel)
        name = view.findViewById(R.id.activityName)
        desc = view.findViewById(R.id.activityDesc)
        kcalReduction = view.findViewById(R.id.kcalReduction)

        addActivity.setOnClickListener {
            if(name.text.isBlank() || desc.text.isBlank() || kcalReduction.text.isBlank() )
                Toast.makeText(requireContext(),"Fill in all inputs", Toast.LENGTH_SHORT).show()
            else
            {
                newActivity = Activity(name.text.toString(),desc.text.toString(),kcalReduction.text.toString().toFloat())
                //TODO save activity to file and go back to previous screen
                val fragment = AddActivityScreen()
                val fragmentManager = activity?.supportFragmentManager
                fragmentManager?.beginTransaction()?.replace(R.id.newActivityScreen, fragment)?.addToBackStack(null)?.commit()
            }
        }

        cancel.setOnClickListener {
            val fragment = AddActivityScreen()
            val fragmentManager = activity?.supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.newActivityScreen, fragment)?.addToBackStack(null)?.commit()
        }
        return view
    }
}