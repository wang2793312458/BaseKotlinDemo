package com.example.basekotlindemo.http

/**
 * Created by iCong on 2017/6/28.
 */
object TokenManager {
    private var token: String? = null
    
    fun setToken(token: String) {
        TokenManager.token = token
    }
    
    fun getToken(): String? {
        return token
    }
}