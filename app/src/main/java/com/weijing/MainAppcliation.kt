package com.weijing

import android.app.Application
import android.util.Log
import com.weijing.kotlin.utils.OtherUtils

/**
 * Created by kxf on 2017/10/31.
 */
class MainAppcliation : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.e("kxf", "MainAppcliation")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        OtherUtils.isNetWorkConnect(this)
    }


}
