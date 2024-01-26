package com.example.dietapp.Activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dietapp.Food.ProductQuantityDialog
import com.example.dietapp.Main.MainActivityInterface
import com.example.dietapp.Main.MainActivityModel
import com.example.dietapp.R
import com.example.dietapp.backend.Activity
import com.example.dietapp.backend.Product

class ActivityScreen : Fragment() {

    private lateinit var searchEdit: EditText
    private lateinit var addActivityButton: Button
    private lateinit var activityRecycler: RecyclerView

    private lateinit var mainActivityModel: MainActivityModel

    private lateinit var filteredItems: MutableList<Activity>
    private lateinit var adapter: ActivityListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        container?.removeAllViews()

        val view = inflater.inflate(R.layout.activity_screen, container, false)

        mainActivityModel = ViewModelProvider(requireActivity()).get(MainActivityModel::class.java)
        filteredItems = mainActivityModel.activities.toMutableList()
        adapter = ActivityListAdapter(filteredItems, ::useActivity, ::editActivity)

        searchEdit = view.findViewById(R.id.SearchEdit)
        addActivityButton = view.findViewById(R.id.AddActivityButton)
        activityRecycler = view.findViewById(R.id.ActivityRecycler)

        activityRecycler.layoutManager = LinearLayoutManager(context)
        activityRecycler.adapter = adapter

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as? MainActivityInterface)?.backToMainButton()
            }
        })

        addActivityButton.setOnClickListener {
            (activity as? MainActivityInterface)?.activityToCreateActivityButton()
        }

        searchEdit.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchRecyclerView(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        return view
    }

    fun searchRecyclerView(searchValue: String) {
        filteredItems = mainActivityModel.activities.toMutableList()
        if (searchValue.isNotBlank()){
            filteredItems = filteredItems.filter { item ->
                item.name.contains(searchValue, ignoreCase = true)
            }.toMutableList()
        }
        adapter.setFilteredItems(filteredItems)
    }

    fun editActivity(activityIndex: Int){
        mainActivityModel.editedActivityIndex = activityIndex
        (activity as? MainActivityInterface)?.activityToCreateActivityButton()
    }

    fun onNumberChosen(number: Float, activity: Activity) {
        var amount = number.toInt()
        mainActivityModel.user.AddActivity(amount,activity)
        println("Chosen number: $amount")
    }

    fun useActivity(_activity: Activity){
        val dialog = ActivityDialog(this, _activity)
        dialog.show(parentFragmentManager, "ActivityDialog")
    }

    override fun onStop() {
        mainActivityModel.activities = adapter.getItems()
        super.onStop()
    }
}