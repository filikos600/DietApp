package com.example.dietapp.Products

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
import com.example.dietapp.Main.MainActivityInterface
import com.example.dietapp.Main.MainActivityModel
import com.example.dietapp.R
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

    private var editing = false

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

        loadEditedProductInfo()

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
                if (editing){
                    mainActivityModel.products[mainActivityModel.editedProductIndex] = Product(name,calories, fats, carbohydrates, sugar, proteins, salts, portion)
                    mainActivityModel.editedProductIndex = -1
                    (activity as? MainActivityInterface)?.createProductToProductsButton()
                }
                else{
                    mainActivityModel.products.add(Product(name,calories, fats, carbohydrates, sugar, proteins, salts, portion))
                    (activity as? MainActivityInterface)?.createProductToProductsButton()
                }

            }
        }

        backButton.setOnClickListener {
            (activity as? MainActivityInterface)?.createProductToProductsButton()
        }
        return view
    }

    fun loadEditedProductInfo(){
        val index = mainActivityModel.editedProductIndex
        if (index < 0){
            editing = false
        }
        else{
            val editProduct =  mainActivityModel.products[index]
            editing = true
            nameEdit.setText(editProduct.name)
            caloriesEdit.setText(editProduct.calories.toString())
            fatsEdit.setText(editProduct.fats.toString())
            carbohydratesEdit.setText(editProduct.carbohydrates.toString())
            sugarEdit.setText(editProduct.sugar.toString())
            proteinEdit.setText(editProduct.protein.toString())
            saltEdit.setText(editProduct.salt.toString())
            portionEdit.setText(editProduct.portion.toString())
        }
    }
}