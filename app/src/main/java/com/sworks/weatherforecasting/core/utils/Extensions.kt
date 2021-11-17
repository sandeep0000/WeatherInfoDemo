package com.sworks.weatherforecasting.core.utils

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

fun Activity.setSnackBar(msg: String?): Snackbar {
    return Snackbar.make(findViewById(android.R.id.content), msg!!, Snackbar.LENGTH_LONG)
}

fun Fragment.setSnackBar(msg: String?): Snackbar {
    return activity?.setSnackBar(msg)!!
}

fun Activity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()

fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    if (activity != null) {
        activity!!.toast(text, duration)
    }
}

class Extensions @Inject constructor(private val androidApplication: Application) {

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private fun getNetworkInfo(androidApplication: Application): NetworkInfo? {
        val cm =
            (this.androidApplication.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        return cm.activeNetworkInfo
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isConnected(): Boolean {
        val info = getNetworkInfo(androidApplication)
        return info != null && info.isConnected
    }

}










