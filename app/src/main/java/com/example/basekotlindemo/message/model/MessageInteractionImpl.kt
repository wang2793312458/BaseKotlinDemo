package com.example.basekotlindemo.message.model

import com.example.basekotlindemo.http.HttpFactory
import com.example.basekotlindemo.message.entity.MessageData
import com.example.basekotlindemo.mvp.IBaseInteraction.BaseListener

/**
 * Created by bc_android on 2018/5/22.
 */
class MessageInteractionImpl : MessageInteraction {
    override fun cancel(var1: Int) {

    }

    override fun getMessageList(userId: String, nowPage: Int, listener: BaseListener<ArrayList<MessageData>>) {
        HttpFactory.getInstance()
                .getMessageList(mapOf("" to ""))
                .compose(HttpFactory.schedulers())
                .subscribe({
                    listener.success(it)
                }, {
                    listener.error(it.message!!)
                })
    }
}