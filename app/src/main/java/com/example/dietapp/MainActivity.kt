package com.example.dietapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainActivityInterface {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
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
            R.id.nav_activities -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, AddActivityScreen()).commit()
            R.id.nav_products -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProductsScreen()).commit()
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
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, AddActivityScreen()).commit()
    }

    override fun dishesToFoodButton() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DishesScreen()).commit()
    }

    override fun dishesToProductButton() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FoodScreen()).commit()
    }



//    override fun onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START)
//        }else{
//            onBackPressedDispatcher.onBackPressed()
//        }
//    }
}