package com.example.basekotlindemo.mvp

import com.example.basekotlindemo.message.entity.MessageData

interface IBaseInteraction {
    abstract fun cancel(var1: Int)

    interface BaseListener<T> {
        fun success(var1: ArrayList<MessageData>)

        fun error(var1: String)
    }
}