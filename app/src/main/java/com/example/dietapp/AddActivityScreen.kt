package com.example.dietapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class AddActivityScreen : Fragment() {

    private lateinit var findActivity: Button
    private lateinit var newActivity: Button
    private lateinit var summary: TextView
    private lateinit var page: LinearLayout
    private lateinit var prevPage: Button
    private lateinit var nextPage: Button

    private var pageNumber = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.add_activity_screen, container, false)

        findActivity = view.findViewById(R.id.findActivity)
        newActivity = view.findViewById(R.id.newActivity)
        summary = view.findViewById(R.id.summary)
        page = view.findViewById(R.id.activityPage)
        prevPage = view.findViewById(R.id.prevPage)
        nextPage = view.findViewById(R.id.nextPage)

        for(i in 0..5)
        {
            var button = Button(requireContext())
            var name = "activity" + (pageNumber*6 + i).toString()
            button.text = name
            button.id = i
            var params = LinearLayout.LayoutParams(0,100)
            params.weight = 1f
            params.height = MATCH_PARENT
            params.width = MATCH_PARENT
            page.addView(button, params)
        }

        findActivity.setOnClickListener {
            summary.text = "Finding activity (WIP)"
        }

        newActivity.setOnClickListener {
            summary.text = "Creating new activity (WIP)"
        }

        prevPage.setOnClickListener{
            pageNumber -= 1
            summary.text = "Prev page (WIP)"
        }

        nextPage.setOnClickListener{
            pageNumber += 1
            summary.text = "Next page (WIP)"
        }
        return view
    }
}