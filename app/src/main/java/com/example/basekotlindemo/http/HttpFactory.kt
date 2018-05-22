package com.example.basekotlindemo.http

import com.example.basekotlindemo.common.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by iCong on 2017/6/26.
 */
object HttpFactory {
    private val client = OkHttpClient.Builder()
          .readTimeout(30, TimeUnit.SECONDS)
          .writeTimeout(20, TimeUnit.SECONDS)
          .addInterceptor(HttpInterceptor())
          .build()
    
    private val retrofit = Retrofit.Builder()
    
    fun getInstance(): Api {
        return retrofit
              .baseUrl(Api.API_DEFAULT_HOST)
              .client(client)
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .addConverterFactory(HttpResultConverter()).build()
              .create(Api::class.java)
    }
    
    fun <T> schedulers(): io.reactivex.ObservableTransformer<T, T> {
        return io.reactivex.ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io()).observeOn(
                  AndroidSchedulers.mainThread())
        }
    }
}