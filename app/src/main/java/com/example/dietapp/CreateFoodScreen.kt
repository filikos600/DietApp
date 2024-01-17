package com.example.dietapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dietapp.backend.Product

class CreateFoodScreen: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()
        val view = inflater.inflate(R.layout.create_product_screen, container, false)
        return view
    }
}