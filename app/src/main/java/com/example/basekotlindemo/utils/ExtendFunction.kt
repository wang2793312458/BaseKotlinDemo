package com.example.basekotlindemo.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.basekotlindemo.common.Api
import com.example.basekotlindemo.http.TokenManager
import java.security.MessageDigest

/**
 * Created by iCong on 2016/7/24.
 */
// String md5
fun String.md5(): String? {
    val content: String
    val token: String? = TokenManager.getToken()
    content = if (TextUtils.isEmpty(token)) {
        "content=$this"
    } else {
        "token=$token&content=$this"
    }
    val messageDigest: MessageDigest?
    try {
        messageDigest = MessageDigest.getInstance("MD5")
        messageDigest?.reset()
        messageDigest.update(content.toByteArray(charset("UTF-8")))
    } catch (e: Exception) {
        return null
    }

    val byteArray = messageDigest.digest()
    val md5StrBuff = StringBuffer()
    for (i in byteArray.indices) {
        if (Integer.toHexString(0xFF and byteArray[i].toInt()).length == 1)
            md5StrBuff.append("0").append(
                    Integer.toHexString(0xFF and byteArray[i].toInt()))
        else
            md5StrBuff.append(Integer.toHexString(0xFF and byteArray[i].toInt()))
    }
    return md5StrBuff.toString().toLowerCase()
}

// getActivity()
fun Activity.getActivity(): Activity {
    return this
}


// 是否显示密码
fun EditText.setShowOrHidePassword(isShow: Boolean) {
    transformationMethod = if (!isShow) {
        PasswordTransformationMethod.getInstance()
    } else {
        HideReturnsTransformationMethod.getInstance()
    }
    setSelection(length())
}

/**
 * 判断网络类型
 *
 * @return -1：没有网络  1：WIFI网络 3：net网络
 */
fun Fragment.getNetType(): Int {
    var netType = -1
    val connMgr = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connMgr.activeNetworkInfo ?: return netType
    val nType = networkInfo.type
    if (nType == ConnectivityManager.TYPE_MOBILE) {
        netType = 3
    } else if (nType == ConnectivityManager.TYPE_WIFI) {
        netType = 1
    }
    return netType
}

/**
 * 判断是否有网
 */
fun Fragment.haveNet(): Boolean {
    return getNetType() != -1
}

/**
 * 判断网络类型
 *
 * @return -1：没有网络  1：WIFI网络 3：net网络
 */
fun Activity.getNetType(): Int {
    var netType = -1
    val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connMgr.activeNetworkInfo ?: return netType
    val nType = networkInfo.type
    if (nType == ConnectivityManager.TYPE_MOBILE) {
        netType = 3
    } else if (nType == ConnectivityManager.TYPE_WIFI) {
        netType = 1
    }
    return netType
}

/**
 * 判断是否有网
 */
fun Activity.haveNet(): Boolean {
    return getNetType() != -1
}

// 加载图片
fun ImageView.loadCircleUrl(url: String) {
    val path = Api.API_LOAD_IMAGE + url
    val options = RequestOptions().circleCrop()
    Glide.with(context).load(path).apply(options).into(this)
}

// 加载图片
fun ImageView.loadCircleBitmap(bit: Bitmap) {
    val options = RequestOptions().circleCrop()
    Glide.with(context).load(bit).apply(options).into(this)
}

// 加载图片
fun ImageView.loadImage(url: String) {
    val path = Api.API_LOAD_IMAGE + url
    Glide.with(context).load(path).into(this)
}

// 密码判断
fun String.checkPassword(): Boolean {
    return this.length >= 6
}