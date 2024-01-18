package com.example.dietapp.Activity

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dietapp.MainActivityInterface
import com.example.dietapp.MainActivityModel
import com.example.dietapp.Products.ProductsListAdapter
import com.example.dietapp.R
import com.example.dietapp.backend.Activity
import com.example.dietapp.backend.Product

class ActivityScreen : Fragment() {

    private lateinit var searchView: TextView
    private lateinit var addActivityButton: Button
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var detailsView: TextView
    private lateinit var amountSelector: EditText
    private lateinit var addButton: Button
    private lateinit var activityRecycler: RecyclerView

    private lateinit var mainActivityModel: MainActivityModel
    private lateinit var selectedActivity: Activity

    private var filter = ""
    private var buttons: MutableList<Button> = arrayListOf<Button>()
    private var pageNumber = 0
    private var activities: MutableList<Activity> = arrayListOf<Activity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.activity_screen, container, false)

        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)

        searchView = view.findViewById(R.id.SearchView)
        addActivityButton = view.findViewById(R.id.AddActivityButton)
        activityRecycler = view.findViewById(R.id.ActivityRecycler)
        previousButton = view.findViewById(R.id.PreviousButton)
        nextButton = view.findViewById(R.id.NextButton)
        detailsView = view.findViewById(R.id.DetailsView)
        amountSelector = view.findViewById(R.id.AmountSelector)
        addButton = view.findViewById(R.id.AddButton)

        activityRecycler.layoutManager = LinearLayoutManager(context)
        activityRecycler.adapter = ActivityListAdapter(mainActivityModel.activities, ::showActivityInfo)

//        searchView.setOnEditorActionListener { _, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_DONE ||
//                (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
//            ) {
//                // Perform your action here
//                filter = searchView.text.toString()
//                pageNumber = 0
//                setButtonsForActivities(findActivities(filter),pageNumber)
//
//
//                // Return true to consume the event
//                true
//            } else {
//                // Return false to let the system handle the event
//                false
//            }
//        }

        addButton.setOnClickListener {
            if(::selectedActivity.isInitialized && amountSelector.text.isNotBlank()) {
                var amount = amountSelector.text.toString().toInt()
                mainActivityModel.user.AddActivity(amount, selectedActivity)
                (activity as? MainActivityInterface)?.backToMainButton()
            }
        }

        addActivityButton.setOnClickListener {
            (activity as? MainActivityInterface)?.activityToCreateActivityButton()
        }

        return view
    }

//    fun findActivities(name: String): MutableList<Activity>
//    {
//        if(name.isBlank())
//            return activities
//        val foundActivities: MutableList<Activity> = arrayListOf<Activity>()
//        for(activity in activities)
//        {
//            if(activity.name.startsWith(name, true))
//                foundActivities.add(activity)
//        }
//        return foundActivities
//    }

    fun showActivityInfo(activity: Activity){
        selectedActivity = activity
        detailsView.text = activity.printActivityInfo()
    }
}