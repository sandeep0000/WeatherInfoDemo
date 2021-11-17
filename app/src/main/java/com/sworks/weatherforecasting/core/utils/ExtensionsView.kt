package com.sworks.weatherforecasting.core.utils

import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar


fun View.snackBarError(msg: String, action: String, event: View.OnClickListener ) {
    val snackBar = Snackbar.make(this, msg, Snackbar.LENGTH_LONG)
    with(snackBar) {
        setAction(action, event)
        setDuration(LENGTH_INDEFINITE)
    }
    snackBar.show()
}

fun View.snackBarError(msg: String) {
    val snackBar = Snackbar.make(this, msg, Snackbar.LENGTH_LONG)
    snackBar.show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}