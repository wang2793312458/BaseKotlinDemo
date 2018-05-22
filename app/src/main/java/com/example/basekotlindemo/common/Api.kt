package com.example.basekotlindemo.common

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface Api {

    companion object {
        const val API_DEFAULT_HOST = "http://39.104.75.162:8080/FireProtection/mobile/"
        const val API_LOAD_IMAGE = "http://39.104.75.162:8080/images/"
        const val API_UPLOAD_IMAGE = "http://39.104.75.162:8080/fileUpload/"
    }

    // 上传文件
    @Multipart
    @POST("file/uploadImages")
    fun upload(@Part file: MultipartBody.Part): Observable<List<String>>

    // 上传文件
    @Multipart
    @POST("file/uploadImages")
    fun uploads(@Part files: List<MultipartBody.Part>): Observable<List<String>>

    // 下载
    @GET
    @Streaming
    fun download(@Url fileUrl: String): Observable<ResponseBody>

    // 获取验证码
    @POST("sys/getSMS")
    fun getCode(@Body map: Map<String, String>): Observable<Boolean>
}