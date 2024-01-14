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
import java.util.Calendar

class CreateProductScreen  : Fragment(){
    private lateinit var imageView: TextView
    private lateinit var nameEdit: EditText
    private lateinit var caloriesEdit: EditText
    private lateinit var portionEdit: EditText
    private lateinit var nutricionalValuesView: TextView
    private lateinit var addButton: Button
    private lateinit var backButton: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.create_product_screen, container, false)

        imageView = view.findViewById(R.id.ImageEdit)
        nameEdit = view.findViewById(R.id.NameEdit)
        caloriesEdit = view.findViewById(R.id.CaloriesEdit)
        portionEdit = view.findViewById(R.id.PortionEdit)
        nutricionalValuesView = view.findViewById(R.id.NutricionalValuesView)
        addButton = view.findViewById(R.id.AddButton)
        backButton = view.findViewById(R.id.BackButton)


        addButton.setOnClickListener {

            var name = nameEdit.text.toString().trim()
            var calories = caloriesEdit.text.toString().trim()
            var portion = portionEdit.text.toString().trim()

            if (name.isEmpty() || calories.isEmpty() || portion.isEmpty()){
                Toast.makeText(requireContext(),"Please fill all info", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(),"added $name product, $calories calories and $portion portion", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            val fragment = DishesScreen()
            val fragmentManager = activity?.supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.createProductScreen, fragment)?.addToBackStack(null)?.commit()
        }
        return view
    }
}