package com.example.dietapp

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dietapp.backend.Activity
import com.example.dietapp.backend.User

class ActivityScreen : Fragment() {

    private lateinit var searchView: TextView
    private lateinit var addActivityButton: Button
    private lateinit var activityLayout: LinearLayout
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var detailsView: TextView
    private lateinit var amountSelector: EditText
    private lateinit var addButton: Button

    private lateinit var mainActivityModel: MainActivityModel
    private lateinit var selectedActivity: Activity

    private var filter = ""
    private var buttons: MutableList<Button> = arrayListOf<Button>()
    private var pageNumber = 0
    private var activities: MutableList<Activity> = arrayListOf<Activity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        createTestActivities()

        container?.removeAllViews()

        val view = inflater.inflate(R.layout.activity_screen, container, false)

        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)

        searchView = view.findViewById(R.id.SearchView)
        addActivityButton = view.findViewById(R.id.AddActivityButton)
        activityLayout = view.findViewById(R.id.ActivityLayout)
        previousButton = view.findViewById(R.id.PreviousButton)
        nextButton = view.findViewById(R.id.NextButton)
        detailsView = view.findViewById(R.id.DetailsView)
        amountSelector = view.findViewById(R.id.AmountSelector)
        addButton = view.findViewById(R.id.AddButton)

        for(i in 0..5)
        {
            val button = Button(requireContext())
            button.setOnClickListener {
                try {
                    val index = activities.indexOfFirst { activity -> activity.name == button.text }
                    val activity = activities.get(index)
                    selectedActivity = activity
                    detailsView.text = activity.printActivityInfo()
                }
                catch(_: Exception)
                {

                }
            }
            buttons.add(button)
            val params = LinearLayout.LayoutParams(0,100)
            params.weight = 1f
            params.height = MATCH_PARENT
            params.width = MATCH_PARENT
            activityLayout.addView(button, params)
        }
        setButtonsForActivities(activities, 0)

        searchView.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                // Perform your action here
                filter = searchView.text.toString()
                pageNumber = 0
                setButtonsForActivities(findActivities(filter),pageNumber)


                // Return true to consume the event
                true
            } else {
                // Return false to let the system handle the event
                false
            }
        }

        addButton.setOnClickListener {
            if(::selectedActivity.isInitialized && amountSelector.text.isNotBlank()) {
                var amount = amountSelector.text.toString().toInt()
                mainActivityModel.user.AddActivity(amount, selectedActivity)
            }
        }

        addActivityButton.setOnClickListener {
            (activity as? MainActivityInterface)?.activityToCreateActivityButton()
        }

        previousButton.setOnClickListener{
            val filteredActivities = findActivities(filter)
            if(pageNumber > 0)
                pageNumber -= 1
            setButtonsForActivities(filteredActivities,pageNumber)
        }

        nextButton.setOnClickListener{
            val filteredActivities = findActivities(filter)
            if(pageNumber < filteredActivities.size/6)
                pageNumber += 1
            setButtonsForActivities(filteredActivities,pageNumber)
        }
        return view
    }

    fun findActivities(name: String): MutableList<Activity>
    {
        if(name.isBlank())
            return activities
        val foundActivities: MutableList<Activity> = arrayListOf<Activity>()
        for(activity in activities)
        {
            if(activity.name.startsWith(name, true))
                foundActivities.add(activity)
        }
        return foundActivities
    }

    fun setButtonsForActivities(activities: MutableList<Activity>, pageNumber: Int)
    {
        for(i in 0..5)
        {
            var button = buttons[i]
            button.setVisibility(View.VISIBLE)
            try {
                button.text = activities[pageNumber*6 + i].name
            }
            catch(_: Exception)
            {
                button.setVisibility(View.INVISIBLE)
            }
        }
    }

    fun createTestActivities()
    {
        for(i in 0..10)
        {
            val name = "test$i"
            val activity = Activity(name, "test", 20f)
            activities.add(activity)
        }
    }
}