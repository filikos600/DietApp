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
import androidx.fragment.app.FragmentActivity
import com.example.dietapp.backend.Activity
import com.example.dietapp.backend.User
import kotlin.math.floor

class AddActivityScreen : Fragment() {

    private lateinit var searchView: TextView
    private lateinit var addActivityButton: Button
    private lateinit var activityLayout: LinearLayout
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var detailsView: TextView
    private lateinit var amountSelector: EditText
    private lateinit var addButton: Button

    private lateinit var user: User
    private lateinit var filter: String
    private lateinit var selectedActivity: Activity

    private var pageNumber = 0
    private var activities: ArrayList<Activity> = arrayListOf<Activity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.add_activity_screen, container, false)

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
            button.id = i
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
            if(!amountSelector.text.isBlank())
            {
                user.AddActivity(amountSelector.text.toString().toInt(), selectedActivity)
            }
        }

        addActivityButton.setOnClickListener {
            val fragment = NewActivityScreen()
            val fragmentManager = activity?.supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.addActivityScreen, fragment)?.addToBackStack(null)?.commit()
        }

        previousButton.setOnClickListener{
            val filteredActivities = findActivities(filter)
            if(pageNumber > 0)
                pageNumber -= 1
            setButtonsForActivities(filteredActivities,pageNumber)
        }

        nextButton.setOnClickListener{
            val filteredActivities = findActivities(filter)
            if(pageNumber < floor(filteredActivities.size.toFloat()/6.0f))
                pageNumber += 1
            setButtonsForActivities(filteredActivities,pageNumber)
        }
        return view
    }

    fun findActivities(name: String): ArrayList<Activity>
    {
        var foundActivities: ArrayList<Activity> = arrayListOf<Activity>()
        for(activity in activities)
        {
            if(activity.name.startsWith(name, true))
                foundActivities.add(activity)
        }
        return foundActivities
    }

    fun setButtonsForActivities(activities: ArrayList<Activity>, pageNumber: Int)
    {
        for(i in 0..5)
        {
            try {
                var button: Button?
                button = view?.findViewById(i)
                button?.text = activities.get(pageNumber * 6 + i).name
            }
            catch(_: Exception)
            { }
        }
    }
}