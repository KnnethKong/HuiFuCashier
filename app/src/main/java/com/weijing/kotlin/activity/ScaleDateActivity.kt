package com.weijing.kotlin.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import com.weijing.R
import com.weijing.kotlin.adpter.SampleDataAdapter
import com.weijing.kotlin.entity.DayScaleEntity
import com.weijing.kotlin.entity.DayScaleRespons
import com.weijing.kotlin.entity.HttpResult
import com.weijing.kotlin.entity.SampleEntity
import com.weijing.kotlin.serverapi.APIService
import com.weijing.kotlin.utils.Preference
import com.weijing.kotlin.utils.RetrofitUtils
import com.weijing.kotlin.utils.SampleItemClick
import kotlinx.android.synthetic.main.dayscale_layout.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by kxf on 2017/11/2.
 */
class ScaleDateActivity : AppCompatActivity(), SampleItemClick {
    //private val data: ArrayList<SampleEntity>
    private var token: String by Preference<String>(this, "token", "")
    private var userId: Long by Preference<Long>(this, "userId", 0)
    private var storeId: Long by Preference<Long>(this, "storeId", 0)

    //private var adapter: SampleDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dayscale_layout)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.title = "日结"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { view -> finish() }
        getData()
    }

    override fun click(position: Long) {
        super.click(position)

    }

    private fun getData() {
        var page = JSONObject()
        page.put("pageNO", 100)
        page.put("everyPageCount", 1)
        var dto = JSONObject()
        dto.put("payStartTime", "2017-05-05")
        dto.put("payEndTime", "2017-08-05")
        dto.put("realname", null)
        dto.put("status", -1)
        dto.put("storeId", storeId)
        dto.put("storeuseid", userId)
        var json = JSONObject()
        json.put("dto", dto)
        json.put("page", page)
        var param = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString())
        var retrofit = RetrofitUtils.getIntance()
        var api = retrofit.create(APIService::class.java)
        val call = api.dateScalePre(token, param)
        call.enqueue(object : Callback<HttpResult<DayScaleRespons>> {
            override fun onFailure(call: Call<HttpResult<DayScaleRespons>>?, t: Throwable?) {
                Log.e("kxflog", t.toString())
                Snackbar.make(dayscale_rv, "出现错误:" + t!!.message, Snackbar.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<HttpResult<DayScaleRespons>>?, response: Response<HttpResult<DayScaleRespons>>?) {
//                println(response.body().success)
                if (response!!.body().success) {
                    var entity = response.body().data.merchantCountOrderCommon
                    setData(entity)
                }
            }
        })
    }

    private fun setData(entity: DayScaleEntity) {
        var s1 = SampleEntity("总金额", entity.totalAmount, 1)
        var s2 = SampleEntity("支付宝笔数", entity.aliPayCount, 2)
        var s3 = SampleEntity("支付宝商户实收", entity.aliPayPaidAmount, 3)
        var s4 = SampleEntity("支付宝退款金额", entity.aliPayRefundSum, 4)
        var s5 = SampleEntity("支付宝订单总金额", entity.aliPaySum, 5)
        var s6 = SampleEntity("开始时间", entity.beginTime, 6)
        var s7 = SampleEntity("卡消费金额", entity.cardTotalAmount, 7)
        // var s8 = SampleEntity("总金额", entity.discountAmount, 1)
        var s9 = SampleEntity("结束时间", entity.endTime, 9)
        var s10 = SampleEntity("商户总收款", entity.merchantTotalAmount, 10)
        var s11 = SampleEntity("实际支付", entity.realPayAmount, 11)
        var s12 = SampleEntity("总退款金额", entity.refundAmount, 12)
        var s13 = SampleEntity("服务费", entity.serviceAmount, 13)
        var s14 = SampleEntity("微信订单笔数", entity.wxPayCount, 14)
        var s15 = SampleEntity("微信商户实收", entity.wxPayPaidAmount, 15)
        var s16 = SampleEntity("微信订单金额", entity.wxPaySum, 16)
        var s17 = SampleEntity("微信退款金额", entity.wxRefundSum, 17)
        var list = mutableListOf(s1, s2, s3, s4, s5, s6, s7, s9, s10, s11, s12, s13, s14, s15, s15, s16, s17)
        dayscale_rv.layoutManager = LinearLayoutManager(this)
        dayscale_rv.adapter = SampleDataAdapter(list, this)
    }
}