package com.example.basekotlindemo.message.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basekotlindemo.R
import com.example.basekotlindemo.message.entity.MessageData
import kotlinx.android.synthetic.main.item_message.view.*

/**
 * Created by bc_android on 2018/5/22.
 */
class MessageAdapter : RecyclerView.Adapter<MessageAdapter.Holder>() {
    private var list = arrayListOf<MessageData>()

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.itemView.tvTitle.text = data.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message, parent, false))
    }

    fun setList(list: ArrayList<MessageData>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun addList(list: ArrayList<MessageData>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}