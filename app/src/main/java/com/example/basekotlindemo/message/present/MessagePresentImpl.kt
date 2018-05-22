package com.example.basekotlindemo.message.present

import android.support.v7.widget.RecyclerView
import com.example.basekotlindemo.message.adapter.MessageAdapter
import com.example.basekotlindemo.message.entity.MessageData
import com.example.basekotlindemo.message.model.MessageInteraction
import com.example.basekotlindemo.message.view.MessageView
import com.example.basekotlindemo.mvp.IBaseInteraction.BaseListener
import com.example.basekotlindemo.utils.RecyclerViewUtil

/**
 * Created by bc_android on 2018/5/22.
 */
class MessagePresentImpl : MessagePresent, BaseListener<ArrayList<MessageData>> {
    private var nowPage = 1
    private var PAGE_SIZE = 20
    private var mInteraction: MessageInteraction? = null
    private var isRefresh = true
    private var mView: MessageView? = null
    private var adapter: MessageAdapter? = null

//    fun MessagePresentImpl(view: MessageView) {
//        mView = view
//        mInteraction = MessageInteractionImpl()
//    }
//
    override fun onDestroy() {
    }

    override fun onStart() {
        mView!!.showProgressBar()
        getMessageList()
    }

    private fun getMessageList() {
        mInteraction!!.getMessageList("", nowPage, this)
    }

    override fun attachRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = MessageAdapter()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (RecyclerViewUtil.isScrollBottom(
                        recyclerView) && recyclerView.adapter.itemCount % PAGE_SIZE == 0) {
                    isRefresh = false
                    nowPage++
                    mView!!.showProgressBar()
                    getMessageList()
                }
            }
        })
    }

    override fun refresh() {
        mView!!.showProgressBar()
    }

    override fun success(var1: ArrayList<MessageData>) {
        mView!!.hideProgressBar()
        if (isRefresh) {
            adapter!!.setList(var1)
        } else {
            adapter!!.addList(var1)
        }
    }

    override fun error(var1: String) {
        mView!!.showMsg(var1)
        mView!!.showEmpty()
    }
}