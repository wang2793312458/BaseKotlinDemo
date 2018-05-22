package com.example.basekotlindemo.message

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.example.basekotlindemo.R
import com.example.basekotlindemo.common.BaseActivity
import com.example.basekotlindemo.message.present.MessagePresent
import com.example.basekotlindemo.message.present.MessagePresentImpl
import com.example.basekotlindemo.message.view.MessageView
import kotlinx.android.synthetic.main.activity_message.*
import org.jetbrains.anko.act
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast

class MessageActivity : BaseActivity(), MessageView {

    private var mPresent: MessagePresent? = null
    override fun getContentViewLayoutId(): Int {
        return R.layout.activity_message
    }

    override fun initView(savedInstanceState: Bundle?) {
//        mPresent = MessagePresentImpl(this)
        mPresent = MessagePresentImpl()
        srRefresh.setColorSchemeResources(R.color.colorAccent)
        (mPresent as MessagePresentImpl).attachRecyclerView(rvList)
        srRefresh.onRefresh {
            (mPresent as MessagePresentImpl).refresh()
        }
    }

    override fun getCurrentActivity(): Activity {
        return act
    }

    override fun showMsg(var1: String) {
        toast(var1)
    }

    override fun showProgressBar() {
        showLoading()
    }

    override fun hideProgressBar() {
        showContent()
    }

    override fun showNetError() {
        showNetError(View.OnClickListener {
            toast("再来一次")
        })
    }

    override fun showEmpty() {
        showEmpty(View.OnClickListener {
            toast("再来一次")
        })
    }

    override fun onResume() {
        super.onResume()
        mPresent!!.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresent!!.onDestroy()
    }
}
