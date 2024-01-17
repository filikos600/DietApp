package com.example.dietapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dietapp.backend.Product

class CreateProductScreen  : Fragment(){
    private lateinit var imageView: TextView
    private lateinit var nameEdit: EditText
    private lateinit var caloriesEdit: EditText
    private lateinit var fatsEdit: EditText
    private lateinit var carbohydratesEdit: EditText
    private lateinit var sugarEdit: EditText
    private lateinit var proteinEdit: EditText
    private lateinit var saltEdit: EditText
    private lateinit var portionEdit: EditText
    private lateinit var addButton: Button
    private lateinit var backButton: Button

    private lateinit var mainActivityModel: MainActivityModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.create_product_screen, container, false)

        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)

        imageView = view.findViewById(R.id.ImageEdit)
        nameEdit = view.findViewById(R.id.NameEdit)
        caloriesEdit = view.findViewById(R.id.CaloriesEdit)
        fatsEdit = view.findViewById(R.id.FatsEdit)
        carbohydratesEdit = view.findViewById(R.id.CarbohydratesEdit)
        sugarEdit = view.findViewById(R.id.SugarEdit)
        proteinEdit = view.findViewById(R.id.ProteinEdit)
        saltEdit = view.findViewById(R.id.SaltEdit)
        portionEdit = view.findViewById(R.id.PortionEdit)
        addButton = view.findViewById(R.id.AddButton)
        backButton = view.findViewById(R.id.BackButton)


        addButton.setOnClickListener {

            val name = nameEdit.text.toString().trim()
            val calories = if (caloriesEdit.text.toString().trim().isEmpty()) 0f else caloriesEdit.text.toString().trim().toFloat()
            val fats = if (fatsEdit.text.toString().trim().isEmpty()) 0f else fatsEdit.text.toString().trim().toFloat()
            val carbohydrates = if (carbohydratesEdit.text.toString().trim().isEmpty()) 0f else carbohydratesEdit.text.toString().trim().toFloat()
            val sugar = if (sugarEdit.text.toString().trim().isEmpty()) 0f else sugarEdit.text.toString().trim().toFloat()
            val proteins = if (proteinEdit.text.toString().trim().isEmpty()) 0f else proteinEdit.text.toString().trim().toFloat()
            val salts = if (saltEdit.text.toString().trim().isEmpty()) 0f else saltEdit.text.toString().trim().toFloat()
            val portion = if (portionEdit.text.toString().trim().isEmpty()) 0f else portionEdit.text.toString().trim().toFloat()

            if (name.isEmpty() || calories <= 0f){
                Toast.makeText(requireContext(),"Name and calories are required", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(),"added $name product", Toast.LENGTH_SHORT).show()
                val newProduct = Product(name,calories, fats, carbohydrates, sugar, proteins, salts, portion)
                mainActivityModel.products.add(newProduct)
            }
        }

        backButton.setOnClickListener {
            (activity as? MainActivityInterface)?.createProductToProductsButton()
        }
        return view
    }
}