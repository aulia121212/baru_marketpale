package com.example.umxplore

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.umxplore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        // Get NavController to handle fragment navigation
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Check if user is logged in by getting an extra like user_id or login status
        val userId = intent.getIntExtra("user_id", 0)
        val showAkunTerdaftar = intent.getBooleanExtra("show_akun_terdaftar", false)

        if (userId != 0 || showAkunTerdaftar) {
            // If user is logged in, navigate to the AkunTerdaftar fragment
            val bundle = Bundle().apply {
                putInt("user_id", userId)  // Pass the user_id if necessary
            }
            navController.navigate(R.id.navigation_akun_terdaftar, bundle)
        } else {
            // Default fragment navigation if user is not logged in
            navController.navigate(R.id.navigation_akun)
        }

        // Set the status bar color to white and make status bar icons dark
        window.statusBarColor = resources.getColor(android.R.color.white, theme)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        // Optional: Check if intent has extra for showing 'akun_terdaftar' directly
        if (intent.getBooleanExtra("show_akun_terdaftar", false)) {
            navController.navigate(R.id.navigation_akun_terdaftar)
        }

        // Set the AppBar configuration with the menu items for navigation
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_faq,
                R.id.navigation_akun, R.id.navigation_akun_terdaftar
            )
        )

        // Apply window insets to handle padding for system bars (optional UI improvement)
        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            val systemBarsInsets = insets.systemWindowInsets
            view.setPadding(0, 0, 0, systemBarsInsets.bottom)
            insets.consumeSystemWindowInsets()
        }

        // Set up navigation with the NavController and bottom navigation view
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Handle navigation based on selected menu in BottomNavigationView
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home)
                    true
                }
                R.id.navigation_dashboard -> {
                    navController.navigate(R.id.navigation_dashboard)
                    true
                }
                R.id.navigation_faq -> {
                    navController.navigate(R.id.navigation_faq)
                    true
                }
                R.id.navigation_akun -> {
                    // Periksa apakah akun dapat diklik tanpa kondisi login
                    navController.navigate(R.id.navigation_akun)
                    true
                }
                R.id.navigation_akun_terdaftar -> {
                    // Pastikan ini hanya terbuka jika user sudah login
                    if (userId != 0) {
                        navController.navigate(R.id.navigation_akun_terdaftar)
                    } else {
                        navController.navigate(R.id.navigation_akun)
                    }
                    true
                }
                else -> false
            }
        }


        // Hide action bar if not needed
        supportActionBar?.hide()
    }
}
