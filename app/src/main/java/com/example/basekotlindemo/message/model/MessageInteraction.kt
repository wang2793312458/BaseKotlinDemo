package com.example.basekotlindemo.message.model

import com.example.basekotlindemo.message.entity.MessageData
import com.example.basekotlindemo.mvp.IBaseInteraction
import com.example.basekotlindemo.mvp.IBaseInteraction.BaseListener

/**
 * Created by bc_android on 2018/5/22.
 */
interface MessageInteraction : IBaseInteraction {
    fun getMessageList(userId: String, nowPage: Int, listener: BaseListener<ArrayList<MessageData>>)
}