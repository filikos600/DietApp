package com.example.dietapp.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dietapp.Main.MainActivityInterface
import com.example.dietapp.Main.MainActivityModel
import com.example.dietapp.R
import com.example.dietapp.backend.Activity
import com.example.dietapp.backend.Product

class CreateActivityScreen : Fragment() {

    private lateinit var addActivity: Button
    private lateinit var cancel: Button
    private lateinit var name: EditText
    private lateinit var desc: EditText
    private lateinit var kcalReduction: EditText

    private var editing = false

    private lateinit var mainActivityModel: MainActivityModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.create_activity_screen, container, false)

        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)

        addActivity = view.findViewById(R.id.add)
        cancel = view.findViewById(R.id.cancel)
        name = view.findViewById(R.id.activityName)
        desc = view.findViewById(R.id.activityDesc)
        kcalReduction = view.findViewById(R.id.kcalReduction)

        loadEditedActivityInfo()

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as? MainActivityInterface)?.createActivityToActivityButton()
            }
        })

        addActivity.setOnClickListener {

            val name = name.text.toString().trim()
            val desc = desc.text.toString().trim()
            val kcalBurnt = if (kcalReduction.text.toString().trim().isEmpty()) 0f else kcalReduction.text.toString().trim().toFloat()

            if (name.isEmpty() || kcalBurnt <= 0f){
                Toast.makeText(requireContext(),"Name and calories are required", Toast.LENGTH_SHORT).show()
            } else {
                if (editing){
                    mainActivityModel.activities[mainActivityModel.editedActivityIndex] = Activity(name,desc, kcalBurnt)
                    mainActivityModel.editedProductIndex = -1
                    (activity as? MainActivityInterface)?.createActivityToActivityButton()
                }
                else{
                    mainActivityModel.activities.add(Activity(name,desc, kcalBurnt))
                    (activity as? MainActivityInterface)?.createProductToProductsButton()
                }

            }
        }

        cancel.setOnClickListener {
            (activity as? MainActivityInterface)?.createActivityToActivityButton()
        }
        return view
    }

    fun loadEditedActivityInfo(){
        val index = mainActivityModel.editedActivityIndex
        if (index < 0){
            editing = false
        }
        else{
            val editActivity =  mainActivityModel.activities[index]
            editing = true
            name.setText(editActivity.name)
            desc.setText(editActivity.desc.toString())
            kcalReduction.setText(editActivity.kcalBurnt.toString())
        }
    }
}