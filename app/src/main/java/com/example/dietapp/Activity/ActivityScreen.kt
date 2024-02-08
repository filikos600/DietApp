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
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dietapp.Food.ProductQuantityDialog
import com.example.dietapp.Main.MainActivityInterface
import com.example.dietapp.Main.MainActivityModel
import com.example.dietapp.Products.ProductQuantity
import com.example.dietapp.R
import com.example.dietapp.backend.Activity
import com.example.dietapp.backend.Product

class ActivityScreen : Fragment() {
    private lateinit var searchEdit: EditText
    private lateinit var addActivityButton: Button
    private lateinit var activityRecycler: RecyclerView
    private lateinit var detailsView: TextView
    private lateinit var addButton: Button

    private lateinit var selectedActivity: Activity
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
        adapter = ActivityListAdapter(filteredItems, ::showActivityInfo, ::editActivity)
        detailsView = view.findViewById(R.id.DetailsView)
        addButton = view.findViewById(R.id.AddButton)

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

        addButton.setOnClickListener {
            if(::selectedActivity.isInitialized) {
                val dialog = ActivityDialog(this, selectedActivity)
                dialog.show(parentFragmentManager, "ProductQuantityDialog")
            }
            else{
                Toast.makeText(requireContext(),"Please select from list above", Toast.LENGTH_SHORT).show()
            }
        }

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

        searchRecyclerView("")

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

    fun onNumberChosen(number: Float, _activity: Activity) {
        var amount = number.toInt()
        mainActivityModel.user.AddActivity(amount,_activity)
        (activity as? MainActivityInterface)?.backToMainButton()
    }

    fun showActivityInfo(_activity: Activity){
        selectedActivity = _activity
        detailsView.text = _activity.printActivityInfo()
    }

    override fun onStop() {
        searchRecyclerView("")
        mainActivityModel.activities = adapter.getItems()
        super.onStop()
    }
}