package com.example.dietapp.Food

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.dietapp.R
import com.example.dietapp.backend.Product

class ProductQuantityDialog(private val myParentFragment: CreateFoodScreen, private val product: Product) : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.product_quantity_dialog, null)

            val editTextNumber = dialogView.findViewById<EditText>(R.id.editTextNumber)
            val btnSubmit = dialogView.findViewById<Button>(R.id.btnSubmit)

            btnSubmit.setOnClickListener {
                val inputNumber = editTextNumber.text.toString().toFloatOrNull()

                if (inputNumber != null && inputNumber > 0) {
                    myParentFragment.onNumberChosen(inputNumber, product)
                    dismiss()
                } else {
                    editTextNumber.error = "Please enter a valid number greater than 0"
                }
            }

            builder.setView(dialogView)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}