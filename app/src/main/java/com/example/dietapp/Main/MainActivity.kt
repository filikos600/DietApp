package com.example.dietapp.Main

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.dietapp.Activity.ActivityScreen
import com.example.dietapp.Activity.CreateActivityScreen
import com.example.dietapp.DishesScreen
import com.example.dietapp.Food.CreateFoodScreen
import com.example.dietapp.Food.FoodScreen
import com.example.dietapp.Products.CreateProductScreen
import com.example.dietapp.Products.ProductsScreen
import com.example.dietapp.R
import com.example.dietapp.SettingsScreen
import com.example.dietapp.StatsScreen
import com.example.dietapp.backend.User
import com.google.android.material.navigation.NavigationView
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MainActivityInterface {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var summaryBar: TextView

    private val gson = GsonBuilder().registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter().nullSafe()).create()

    internal class LocalDateTypeAdapter : TypeAdapter<LocalDate>() {

        override fun write(out: JsonWriter, value: LocalDate) {
            out.value(DateTimeFormatter.ISO_LOCAL_DATE.format(value))
        }
        override fun read(input: JsonReader): LocalDate = LocalDate.parse(input.nextString())
    }

    private lateinit var mainActivityModel: MainActivityModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityModel = ViewModelProvider(this).get(MainActivityModel::class.java)
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE)

        LoadCache()

        drawerLayout = findViewById(R.id.drawer_layout)
        summaryBar = findViewById(R.id.short_summary)

        summaryBar.text = printShortSummary()
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.open_nav,
            R.string.close_nav
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainScreen()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }

    }

    private inline fun <reified T> loadObject(key: String, clazz: Class<T>): T? {
        val json = sharedPreferences.getString(key, null)
        return if (json != null) {
            gson.fromJson(json, clazz)
        } else {
            null
        }
    }

    private inline fun <reified T> loadList(key: String): MutableList<T>? {
        val json = sharedPreferences.getString(key, null)
        return if (json != null) {
            val typeToken = TypeToken.getParameterized(MutableList::class.java, T::class.java).type
            gson.fromJson(json, typeToken)
        } else {
            //mutableListOf()
            null
        }
    }

    private fun loadInt(key: String): Int {
        return sharedPreferences.getInt(key, 2000)
    }

    private fun saveInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    private fun saveObject(key: String, value: Any) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(value)
        editor.putString(key, json)
        editor.apply()
    }

    private fun saveList(key: String, list: List<Any>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainScreen()).commit()
            R.id.nav_dishes -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DishesScreen()).commit()
            R.id.nav_activities -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ActivityScreen()).commit()
            R.id.nav_products -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProductsScreen()).commit()
            R.id.nav_stats -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, StatsScreen()).commit()
            R.id.nav_settings -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SettingsScreen()).commit()
        }
        updateSummaryBar()
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun mainToAddDishButton() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setCheckedItem(R.id.nav_dishes)
        updateSummaryBar()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DishesScreen()).commit()
    }

    override fun mainToActivityButton() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setCheckedItem(R.id.nav_activities)
        updateSummaryBar()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ActivityScreen()).commit()
    }

    override fun dishesToFoodButton() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setCheckedItem(R.id.nav_products)
        updateSummaryBar()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FoodScreen()).commit()
    }

    override fun dishesToProductButton() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setCheckedItem(R.id.nav_products)
        updateSummaryBar()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProductsScreen()).commit()
    }

    override fun activityToCreateActivityButton() {
        updateSummaryBar()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CreateActivityScreen()).commit()
    }

    override fun createActivityToActivityButton() {
        updateSummaryBar()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ActivityScreen()).commit()
    }

    override fun createProductToProductsButton(){
        updateSummaryBar()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProductsScreen()).commit()
    }

    override fun createFoodtoFoods(){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FoodScreen()).commit()
    }
    override fun productsToCreateProductButton() {
        updateSummaryBar()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CreateProductScreen()).commit()
    }
    override fun foodsToCreateFoodButton() {
        updateSummaryBar()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CreateFoodScreen()).commit()
    }
    override fun backToMainButton() {
        updateSummaryBar()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainScreen()).commit()
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setCheckedItem(R.id.nav_home)
    }

    override fun updateSummaryBar() {
        summaryBar.text = printShortSummary()
    }

    private fun SaveCache(){
        saveList("products", mainActivityModel.products)
        saveList("foods", mainActivityModel.foods)
        saveList("activities", mainActivityModel.activities)
        saveObject("user", mainActivityModel.user)
        saveInt("goal", mainActivityModel.kcalDailyGoal)
    }

    private fun LoadCache(){
        mainActivityModel.products = loadList("products") ?: mainActivityModel.initializeProductList()
        mainActivityModel.foods = loadList("foods") ?: mainActivityModel.initializeFoodsList()
        mainActivityModel.activities = loadList("activities") ?: mainActivityModel.initializeActivitiesList()
        mainActivityModel.user = loadObject("user", User::class.java) ?: User()
        mainActivityModel.kcalDailyGoal = loadInt("goal")
    }

    override fun onStop() {
        super.onStop()

        SaveCache()

        println("ZAMYKAM")
    }

    private fun printShortSummary(): String {
        val kcal = mainActivityModel.user.getKcalBalance()
        val progress = (kcal / mainActivityModel.kcalDailyGoal) * 100
        var text = ""
        text += kcal.toString()
        text += " kcal ( " + formatFloat(progress,2) + " % )"
        return text
    }
    private fun formatFloat(input: Float, scale: Int): String
    {
        return "%.${scale}f".format(input)
    }
//    override fun onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START)
//        }else{
//            onBackPressedDispatcher.onBackPressed()
//        }
//    }
}