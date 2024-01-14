package com.example.dietapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class StatsScreen : Fragment(){

    private lateinit var previousButton: Button
    private lateinit var dateView: TextView
    private lateinit var nextButton: Button
    private lateinit var infoView: TextView
    private lateinit var dateSpinner: Spinner
    private lateinit var generateButton: Button
    private val calendar = Calendar.getInstance()

    private val spinnerItems = arrayOf("Day", "Week", "Month", "3 Months", "6 Months", "Year")
    private var currentItemIndex = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.stats_screen, container, false)

        previousButton = view.findViewById(R.id.PreviousButton)
        dateView = view.findViewById(R.id.DateView)
        nextButton = view.findViewById(R.id.NextButton)
        infoView = view.findViewById(R.id.infoView)
        dateSpinner = view.findViewById(R.id.spinner)
        generateButton = view.findViewById(R.id.generateButton)

        // Update date
        updateDateLabel()

        // Set up ArrayAdapter for the Spinner
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dateSpinner.adapter = adapter

        // Set listener for Spinner item selection
        dateSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                currentItemIndex = position
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Do nothing
            }
        })

        previousButton.setOnClickListener {
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            updateDateLabel()
        }

        nextButton.setOnClickListener {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            updateDateLabel()
        }

        generateButton.setOnClickListener {
            Toast.makeText(requireContext(),"report not generated, what u gonna do now?", Toast.LENGTH_SHORT).show()
        }

        dateView.setOnClickListener(View.OnClickListener {
            val datePicker = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateDateLabel()
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        })
        return view
    }

    private fun updateDateLabel() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateView.text = dateFormat.format(calendar.time)
    }


}