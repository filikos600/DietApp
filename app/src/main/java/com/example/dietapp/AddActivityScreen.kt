package com.example.dietapp

import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AddActivityScreen : AppCompatActivity() {

    private lateinit var findActivity: Button
    private lateinit var newActivity: Button
    private lateinit var summary: TextView
    private lateinit var page: LinearLayout
    private lateinit var prevPage: Button
    private lateinit var nextPage: Button

    private var pageNumber = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_activity_screen)

        findActivity = findViewById(R.id.findActivity)
        newActivity = findViewById(R.id.newActivity)
        summary = findViewById(R.id.summary)
        page = findViewById(R.id.activityPage)
        prevPage = findViewById(R.id.prevPage)
        nextPage = findViewById(R.id.nextPage)

        for(i in 0..5)
        {
            var button = Button(this)
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

    }
}