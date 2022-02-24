package com.vipulasri.multiplebackstacknavigation.activities

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vipulasri.multiplebackstacknavigation.R
import com.vipulasri.multiplebackstacknavigation.databinding.ActivityMainBinding
import com.vipulasri.multiplebackstacknavigation.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = binding.bottomNavView
        val drawerNavigationView = binding.navView

        val orientation = this.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            bottomNavigationView!!.setupWithNavController(navController)
        } else {
            drawerNavigationView!!.setupWithNavController(navController)
        }
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home, R.id.dashboard, R.id.notifications)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onBackPressed() {
        val notHome = navController.currentDestination!!.label != "Home"
        if (!navController.navigateUp() ||
            (navController.backQueue.size == 3 && notHome)) {
            moveTaskToBack(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val notHome = navController.currentDestination!!.label != "Home"
        if (!navController.navigateUp() ||
            (navController.backQueue.size == 3 && notHome)) {
            moveTaskToBack(true)
            return super.onOptionsItemSelected(item)
        }
        return true
    }
}


