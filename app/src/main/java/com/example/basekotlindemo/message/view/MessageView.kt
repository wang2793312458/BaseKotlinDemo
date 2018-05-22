package com.example.basekotlindemo.message.view

import android.app.Activity
import com.example.basekotlindemo.mvp.IBaseView

/**
 * Created by bc_android on 2018/5/22.
 */
interface MessageView : IBaseView {
    fun getCurrentActivity(): Activity
}