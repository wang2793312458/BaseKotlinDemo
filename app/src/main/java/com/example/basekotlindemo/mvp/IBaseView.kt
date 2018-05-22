package com.example.basekotlindemo.mvp

interface IBaseView {
    abstract fun showProgressBar()

    abstract fun hideProgressBar()

    abstract fun showMsg(var1: String)
}