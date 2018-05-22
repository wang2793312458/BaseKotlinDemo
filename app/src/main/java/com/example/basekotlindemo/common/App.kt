package com.example.basekotlindemo.common

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by bc_android on 2018/5/21.
 */
class App : Application() {
    companion object {
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}