package com.weijing

import android.app.Application
import android.content.Context
import android.util.Log
import com.alibaba.sdk.android.push.CommonCallback
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory
import com.weijing.kotlin.utils.OtherUtils

/**
 * Created by kxf on 2017/10/31.
 */
class MainAppcliation : Application() {
    val TAG: String = "MainAppcliation"

    override fun onCreate() {
        super.onCreate()
        Log.e("kxf", "MainAppcliation")
        initCloudChannel(this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        OtherUtils.isNetWorkConnect(this)
    }

    /**
     * 初始化云推送通道
     * @param applicationContext
     */
    private fun initCloudChannel(applicationContext: Context) {
        PushServiceFactory.init(applicationContext)
        val pushService = PushServiceFactory.getCloudPushService()
        pushService.register(applicationContext, object : CommonCallback {
            override fun onSuccess(suc: String?) {
                Log.d(TAG, "init cloudchannel success : $suc")
                Log.d(TAG, "${pushService.deviceId}")
            }

            override fun onFailed(errorCode: String?, errorMessage: String?) {
                Log.d(TAG, "init cloudchannel failed -- errorcode:$errorCode errorMessage:$errorMessage")
            }


        })
    }

}
