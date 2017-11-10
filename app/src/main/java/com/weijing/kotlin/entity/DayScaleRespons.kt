package com.weijing.kotlin.entity

/**
 * Created by kxf on 2017/11/2.
 */
data class DayScaleRespons(val merchantCountOrderCommon: DayScaleEntity)

data class DayScaleEntity(val totalAmount: String, val totalOrderCount: Int, val refundAmount: String, val realPayAmount: String, val discountAmount: String, val merchantTotalAmount: String
                          , val cardTotalAmount: String, val refundCount: Int, val serviceAmount: String, val beginTime: String, val endTime: String, val merchantId: Int, val storeId: Int
                          , val storeUserId: Int, val wxPaySum: String, val wxRefundSum: String, val wxPayCount: Int, val wxPayPaidAmount: String, val aliPaySum: String, val aliPayRefundSum: String
                          , val aliPayCount: Int, val aliPayPaidAmount: String, val cashPaySum: String, val cashPayRefundSum: String, val cashPayCount: Int, val cashPayPaidAmount: String
                          , val bankPaySum: String, val bankPayRefundSum: String, val bankPayCount: Int, val bankPayPaidAmount: String)
