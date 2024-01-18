package com.example.dietapp.Main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
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
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MainActivityInterface {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainScreen()).commit()
            R.id.nav_dishes -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DishesScreen()).commit()
            R.id.nav_activities -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ActivityScreen()).commit()
            R.id.nav_products -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProductsScreen()).commit()
            R.id.nav_stats -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, StatsScreen()).commit()
            R.id.nav_settings -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SettingsScreen()).commit()
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun mainToAddDishButton() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setCheckedItem(R.id.nav_dishes)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DishesScreen()).commit()
    }

    override fun mainToActivityButton() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setCheckedItem(R.id.nav_activities)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ActivityScreen()).commit()
    }

    override fun dishesToFoodButton() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setCheckedItem(R.id.nav_products)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FoodScreen()).commit()
    }

    override fun dishesToProductButton() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setCheckedItem(R.id.nav_products)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProductsScreen()).commit()
    }

    override fun activityToCreateActivityButton() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CreateActivityScreen()).commit()
    }

    override fun createActivityToActivityButton() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ActivityScreen()).commit()
    }

    override fun createProductToProductsButton(){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProductsScreen()).commit()
    }
    override fun productsToCreateProductButton() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CreateProductScreen()).commit()
    }
    override fun foodsToCreateFoodButton() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CreateFoodScreen()).commit()
    }
    override fun backToMainButton() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainScreen()).commit()
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setCheckedItem(R.id.nav_home)
    }

//    override fun onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START)
//        }else{
//            onBackPressedDispatcher.onBackPressed()
//        }
//    }
}