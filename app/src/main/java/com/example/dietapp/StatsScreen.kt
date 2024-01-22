package com.example.dietapp

import android.app.Activity
import android.app.DatePickerDialog
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dietapp.Main.MainActivityModel
import java.io.File
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.time.LocalDate
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
    private lateinit var mainActivityModel: MainActivityModel
    private var report = ""

    private val createFileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    writeTextToFile(uri, report)
                }
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.stats_screen, container, false)

        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)

        previousButton = view.findViewById(R.id.PreviousButton)
        dateView = view.findViewById(R.id.DateView)
        nextButton = view.findViewById(R.id.NextButton)
        infoView = view.findViewById(R.id.infoView)
        dateSpinner = view.findViewById(R.id.spinner)
        generateButton = view.findViewById(R.id.generateButton)

        val currentDate = calendar.timeInMillis
        // Update date
        updateDateLabel()
        infoView.text = mainActivityModel.user.getUserInfo(calendarToLocalDate())

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
            infoView.text = mainActivityModel.user.getUserInfo(calendarToLocalDate())
        }

        nextButton.setOnClickListener {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            updateDateLabel()
            infoView.text = mainActivityModel.user.getUserInfo(calendarToLocalDate())
        }

        generateButton.setOnClickListener {
            report = ""
            when(currentItemIndex) {
                0 -> report = mainActivityModel.user.getUserInfo()
                1 -> report = mainActivityModel.user.getUserInfo(_Range = 7)
                2 -> report = mainActivityModel.user.getUserInfo(_Range = 30)
                3 -> report = mainActivityModel.user.getUserInfo(_Range = 90)
                4 -> report = mainActivityModel.user.getUserInfo(_Range = 90)
                5 -> report = mainActivityModel.user.getUserInfo(_Range = 180)
                6 -> report = mainActivityModel.user.getUserInfo(_Range = 365)
            }
            requestPermission()
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
            datePicker.datePicker.maxDate = currentDate
            datePicker.show()
        })
        return view
    }

    private fun updateDateLabel() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateView.text = dateFormat.format(calendar.time)
    }

    private fun calendarToLocalDate(): LocalDate
    {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return LocalDate.of(year,month,day)
    }
    private fun requestPermission() {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, "example.txt")
        }
        createFileLauncher.launch(intent)
    }
    private fun writeTextToFile(uri: Uri, text: String) {
        try {
            getActivity()?.getApplicationContext()?.getContentResolver()?.openOutputStream(uri)?.let { outputStream ->
                OutputStreamWriter(outputStream).use { writer ->
                    writer.write(text)
                }
            }
        } catch (_: Exception) {

        }
    }

}