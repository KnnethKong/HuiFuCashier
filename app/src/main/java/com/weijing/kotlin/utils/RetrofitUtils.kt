package com.weijing.kotlin.utils

import com.weijing.kotlin.serverapi.APIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by kxf on 2017/11/1.
 */
class RetrofitUtils private constructor() {

    companion object {
        fun getIntance() = Holder.instance
    }


    private object Holder {
        var okhttp = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build()
        var retro = Retrofit.Builder().baseUrl(OtherUtils.NetUrl).client(okhttp).
                addConverterFactory(ToStringConverterFactory()).
                addConverterFactory(GsonConverterFactory.create()).
                build()
        var instance = retro
    }

    /*  fun init(): Retrofit {
          var okhttp = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build()
          var retro = Retrofit.Builder().baseUrl(OtherUtils.NetUrl).client(okhttp).
                  addConverterFactory(ToStringConverterFactory()).
                  addConverterFactory(GsonConverterFactory.create()).
                  build()
          return retro
      }*/

/*
    //短缓存有效期为10分钟
    val CACHE_STALE_SHORT = 60 * 10
    //长缓存有效期为7天
    val CACHE_STALE_LONG = "60 * 60 * 24 * 7"
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    val CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_LONG
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    val CACHE_CONTROL_NETWORK = "max-age=0"

    var mOkHttpClient: OkHttpClient? = null

    var service: APIService? = null

    init {
        initOkHttpclient()
        var retrofit = Retrofit.Builder()
                .baseUrl(OtherUtils.NetUrl)
                .client(mOkHttpClient)
                //  .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(APIService::class.java)
    }

    companion object {
        fun builder(url: String): RetrofitUtils {
            println(RetrofitUtils.javaClass.classes)
            return RetrofitUtils()
        }
    }


    //配置缓存策略
    fun initOkHttpclient() {
        //   val interceptor = HttpLoggingInterceptor()
        //  interceptor.level = HttpLoggingInterceptor.Level.BODY
        if (mOkHttpClient == null) {
            //    val cache = Cache(File(MyApplication.getInstance().cacheDir, "File_Kotlin"), 14 * 1024 * 100)
            mOkHttpClient = OkHttpClient.Builder()
                    //          .cache(cache)
                    .retryOnConnectionFailure(true)
//                    .addNetworkInterceptor(this)
//                    .addInterceptor(this)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build()
        }
    }

//    //云端响应头拦截器，用来适配缓存策略
//    override fun intercept(chain: Interceptor.Chain?): Response {
//        var request = chain!!.request()
//        if (!NetWorkUtil.isNetWorkConnected()) {
//            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()
//        }
//        var response = chain.proceed(request)
//        if (OtherUtils.isNetWorkConnect(getAppcliation())) {
//            var cacheControl: String = request.cacheControl().toString()
//            return response.newBuilder().header("Cache-Control", cacheControl)
//                    .removeHeader("Pragma").build()
//        } else {
//            return response.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_LONG)
//                    .removeHeader("Pragma").build()
//        }
//    }

    // fun getBanner(): Observable<List<Banner>> = service!!.getHot()*/

}
