package com.niksaen.test.modules

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import androidx.core.content.ContextCompat
import java.util.Locale

sealed class UserCity{
    object Error:UserCity()
    class Success(val location:String): UserCity()
}
fun getUserCity(context: Context): UserCity {
    val permissionStatus = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)

    if(permissionStatus == PackageManager.PERMISSION_GRANTED) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {
            val location =
                locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(location!!.latitude, location.longitude, 1)
            return UserCity.Success(addresses!![0].locality)
        }
    }
    return UserCity.Error
}
