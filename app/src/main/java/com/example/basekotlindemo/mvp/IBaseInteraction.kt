package com.example.basekotlindemo.mvp

interface IBaseInteraction {
    abstract fun cancel(var1: Int)

    interface BaseListener<T> {
        fun success(var1: T)

        fun error(var1: String)
    }
}