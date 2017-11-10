package com.weijing.kotlin.serverapi

import com.weijing.kotlin.entity.DayScaleRespons
import com.weijing.kotlin.entity.HttpResult
import com.weijing.kotlin.entity.UserInfoEntity
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by kxf on 2017/11/1.
 */
interface APIService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("/testSSM/manager-order/search")
    fun orderListShopOwner(@Header("token") header: String, @Body params: RequestBody): Call<String>

    @POST("/testSSM/main/app/login")
    fun getUserToken(@Query("username") username: String, @Query("password") password: String, @Query("cid") cid: String, @Query("macCode") macCode: String): Call<HttpResult<String>>

    @POST("/testSSM/main/myselfnew")
    fun getUserInfo(@Header("token") header: String, @Query("token") token: String): Call<HttpResult<UserInfoEntity>>

    @POST("/main/app/deviceinfo")
    fun settingPrintSound(@Header("token") header: String, @Query("isPrint") isPrint: String, @Query("isSound") isSound: String, @Query("versionCode") versionCode: String, @Query("cid") cid: String, @Query("macCode") macCode: String): Call<String>

    @POST("/testSSM/manager-order/predailysettle")
    fun dateScalePre(@Header("token") header: String,  @Body params: RequestBody): Call<HttpResult<DayScaleRespons>>

}


