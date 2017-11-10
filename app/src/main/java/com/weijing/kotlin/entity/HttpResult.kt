package com.weijing.kotlin.entity

/**
 * Created by kxf on 2017/11/1.
 */
data class HttpResult<T>(val err_code: String, val success: Boolean, val data: T,val err_msg :String)
