package com.mvvm.sandiptest.helper

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService


fun Context.isInternetAvail(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

    return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
}

fun Context.toast(message:String){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}