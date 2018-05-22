package com.example.basekotlindemo.message.present

import android.support.v7.widget.RecyclerView
import com.example.basekotlindemo.mvp.IBasePresent

/**
 * Created by bc_android on 2018/5/22.
 */
interface MessagePresent : IBasePresent {
    fun attachRecyclerView(recyclerView: RecyclerView)

    fun refresh()
}