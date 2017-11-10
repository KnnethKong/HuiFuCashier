package com.weijing.kotlin.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by kxf on 2017/11/1.
 */
object OtherUtils {
    val NetUrl: String = "http://weijing.f3322.net:7070/"
    fun isNetWorkConnect(con: Context): Boolean {
        val cm = con.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val currentNet = cm.activeNetworkInfo ?: return false
        return currentNet.isAvailable
    }


}