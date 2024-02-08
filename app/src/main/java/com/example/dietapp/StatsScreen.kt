package com.example.dietapp

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dietapp.Main.MainActivityInterface
import com.example.dietapp.Main.MainActivityModel
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

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as? MainActivityInterface)?.backToMainButton()
            }
        })

        infoView.movementMethod = ScrollingMovementMethod()

        val currentDate = calendar.timeInMillis
        updateDateLabel()
        infoView.text = mainActivityModel.user.printAllUserDetails(calendarToLocalDate())

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dateSpinner.adapter = adapter

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
            infoView.text = mainActivityModel.user.printAllUserDetails(calendarToLocalDate())
        }

        nextButton.setOnClickListener {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            if (calendar.timeInMillis > currentDate)
                calendar.add(Calendar.DAY_OF_MONTH, -1)
            updateDateLabel()
            infoView.text = mainActivityModel.user.printAllUserDetails(calendarToLocalDate())
        }

        generateButton.setOnClickListener {
            var temp_calendar = Calendar.getInstance()
            temp_calendar.time = calendar.time
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

            report = "Selected timeframe"
            when(currentItemIndex) {
                0 -> report = "Selected day: " + dateFormat.format(temp_calendar.time)+ "\n" + mainActivityModel.user.getUserInfo()
                1 -> {
                    var end_date = temp_calendar.time
                    temp_calendar.add(Calendar.DAY_OF_YEAR, -7)
                    var start_time = temp_calendar.time
                    report = "Selected timeframe: " + dateFormat.format(start_time) + " - " + dateFormat.format(end_date) + "\n" + mainActivityModel.user.getUserInfo(_Range = 7)}
                2 -> {
                    var end_date = temp_calendar.time
                    temp_calendar.add(Calendar.DAY_OF_YEAR, -30)
                    var start_time = temp_calendar.time
                    report = "Selected timeframe: " + dateFormat.format(start_time) + " - " + dateFormat.format(end_date) + "\n" + mainActivityModel.user.getUserInfo(_Range = 30)}
                3 ->  {
                    var end_date = temp_calendar.time
                    temp_calendar.add(Calendar.DAY_OF_YEAR, -90)
                    var start_time = temp_calendar.time
                    report = "Selected timeframe: " + dateFormat.format(start_time) + " - " + dateFormat.format(end_date) + "\n" + mainActivityModel.user.getUserInfo(_Range = 90)}
                4 ->  {
                    var end_date = temp_calendar.time
                    temp_calendar.add(Calendar.DAY_OF_YEAR, -180)
                    var start_time = temp_calendar.time
                    report = "Selected timeframe: " + dateFormat.format(start_time) + " - " + dateFormat.format(end_date) + "\n" + mainActivityModel.user.getUserInfo(_Range = 180)}
                5 ->  {
                    var end_date = temp_calendar.time
                    temp_calendar.add(Calendar.DAY_OF_YEAR, -365)
                    var start_time = temp_calendar.time
                    report = "Selected timeframe: " + dateFormat.format(start_time) + " - " + dateFormat.format(end_date) + "\n" + mainActivityModel.user.getUserInfo(_Range = 365)}
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
            putExtra(Intent.EXTRA_TITLE, "report.txt")
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