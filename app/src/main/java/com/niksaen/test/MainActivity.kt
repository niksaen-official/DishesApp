package com.niksaen.test

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.niksaen.test.databinding.ActivityMainBinding
import com.niksaen.test.modules.UserCity
import java.util.Locale


class MainActivity : AppCompatActivity() {
    var categoryName=""
    lateinit var navController:NavController
    private lateinit var ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController
                ?.setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS)
        }
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        ui.navView.setupWithNavController(navController)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
    }
}