package com.example.basekotlindemo.mvp

interface IBaseView {
    fun showProgressBar()

    fun hideProgressBar()

    fun showNetError()

    fun showEmpty()

    fun showMsg(var1: String)


}