package com.weijing.kotlin.activity

import android.app.LoaderManager
import android.content.Intent
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.weijing.R
import com.weijing.kotlin.entity.HttpResult
import com.weijing.kotlin.entity.UserInfoEntity
import com.weijing.kotlin.serverapi.APIService
import com.weijing.kotlin.utils.Preference
import com.weijing.kotlin.utils.RetrofitUtils
import kotlinx.android.synthetic.main.login_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by kxf on 2017/10/31.
 */
class LoginActivity : AppCompatActivity(), View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor>, TextView.OnEditorActionListener {
    override fun onEditorAction(p0: TextView?, id: Int, key: KeyEvent?): Boolean {
        if (id == EditorInfo.IME_NULL) {
            Snackbar.make(login_btn, "IME_NULL", Snackbar.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    override fun onLoaderReset(p0: Loader<Cursor>?) {
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<Cursor> {
        throw UnsupportedOperationException("not implemented")
    }

    override fun onLoadFinished(p0: Loader<Cursor>?, p1: Cursor?) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun onClick(v: View?) {
        val name: String = login_ed_name.text.toString()
        val pwd: String = login_ed_pwd.text.toString()
        if (name.isNotEmpty() && pwd.isNotEmpty())
            getToken(name, pwd)
        else
            Snackbar.make(login_btn, "不能为空", Snackbar.LENGTH_SHORT).show()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        login_none_ed.clearFocus()
        login_btn.setOnClickListener(this)
        login_ed_pwd.setOnEditorActionListener(this)
        if (storeId != null) {
            var intent = Intent()
            intent.setClass(applicationContext, ScaleDateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getToken(name: String, pwd: String) {
        var retrofit = RetrofitUtils.getIntance()
        var api = retrofit.create(APIService::class.java)
        val call = api.getUserToken(name, pwd, "123", "5685533")
        call.enqueue(object : Callback<HttpResult<String>> {
            override fun onFailure(call: Call<HttpResult<String>>?, t: Throwable?) {
                Snackbar.make(login_btn, "出现错误:" + t!!.message, Snackbar.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<HttpResult<String>>?, response: Response<HttpResult<String>>?) {
                println(response!!.body())
                if (response!!.body().success) {
                    token = response.body().data
                    getUserInfo(token)
                } else {
                    Snackbar.make(login_btn, "出现错误:" + response.body().err_msg, Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }

    private var storeId: Long by Preference<Long>(this, "storeId", 0)
    private var token: String by Preference<String>(this, "token", "")
    private var userId: Long by Preference<Long>(this, "userId", 0)
    private var userType: Int by Preference<Int>(this, "userType", 6)
    private var merchantId: Long by Preference<Long>(this, "merchantId", 0)
    private var storeName: String by Preference<String>(this, "storeName", "")
    private fun getUserInfo(token: String) {
        println("token : $token")
        var retrofit = RetrofitUtils.getIntance()
        var api = retrofit.create(APIService::class.java)
        val call = api.getUserInfo(token, token)
        call.enqueue(object : Callback<HttpResult<UserInfoEntity>> {
            override fun onFailure(call: Call<HttpResult<UserInfoEntity>>?, t: Throwable?) {
                Snackbar.make(login_btn, "出现错误:" + t!!.message, Snackbar.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<HttpResult<UserInfoEntity>>?, response: Response<HttpResult<UserInfoEntity>>?) {
                println(response!!.body())
                var user = response.body().data
                storeId = user.storeId
                userId = user.storeUserId
                merchantId = user.merchantId
                storeName = user.storeName
                userType = user.type
                var intent = Intent()
                intent.setClass(applicationContext, ScaleDateActivity::class.java)
                startActivity(intent)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        System.gc()
    }

}

