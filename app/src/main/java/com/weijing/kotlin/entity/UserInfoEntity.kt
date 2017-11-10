package com.weijing.kotlin.entity

/**
 * Created by kxf on 2017/11/1.
 */
data class UserInfoEntity(val id: Long, val username: String, val storeUserId: Long, val merchantId: Long, val editUsernameCount: Int,val merchantName: String, val storeName: String,
                          val type: Int, val storeId: Long, val name: String, val flagId: String, val payTypeFlag: String, val serviceName: String, val servicePhone: String)
