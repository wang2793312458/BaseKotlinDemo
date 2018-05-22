package com.example.basekotlindemo.common

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.baichang.firecontrol.common.ActivityCollector
import com.example.basekotlindemo.R
import com.example.basekotlindemo.utils.StatusBarUtil
import com.example.basekotlindemo.widget.MultipleStateView
import com.example.basekotlindemo.widget.SystemBarTintManager

/**
 * Created by bc_android on 2018/5/21.
 */
abstract class BaseActivity : FragmentActivity() {
    private var isSystemBar = true
    private var color = -1
    private var isDarkBar = true
    private lateinit var mMultipleStateView: MultipleStateView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView(savedInstanceState)
    }

    private fun initData() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        if (isDarkBar) {
            StatusBarUtil.StatusBarLightMode(this)
        } else {
            StatusBarUtil.StatusBarDarkMode(this, 2)
            StatusBarUtil.StatusBarDarkMode(this, 3)
        }
        if (isSystemBar) {
            initSystemBar()
        }
        //添加相应的布局
        if (getContentViewLayoutId() != 0) {
            mMultipleStateView = MultipleStateView(this)
            val view = View.inflate(this, getContentViewLayoutId(), mMultipleStateView)
            setContentView(view)
        } else {
            throw  IllegalArgumentException("You must return layout id")
        }
        ActivityCollector.addActivity(this)
    }

    fun showLoading() {
        mMultipleStateView.showLoading()
    }

    fun showNetError(onClickListener: View.OnClickListener) {
        mMultipleStateView.showNetError(onClickListener)
    }

    fun showEmpty(onClickListener: View.OnClickListener) {
        mMultipleStateView.showEmpty(onClickListener)
    }

    fun showContent() {
        mMultipleStateView.showContent()
    }


    /**
     * 获取当前布局id
     */
    abstract fun getContentViewLayoutId(): Int


    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 设置是否启用全透明系统栏
     * 默认开启
     *
     * @param isVisible true 启用
     */
    fun setSystemBarTransparent(isVisible: Boolean) {
        isSystemBar = isVisible
    }

    /**
     * 设置之全透明蓝的颜色
     * 默认topBar的颜色即app主色调
     */
    fun setSystemBarColor(color: Int) {
        this.color = color
    }

    private fun initSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            // 状态栏透明 需要在创建SystemBarTintManager 之前调用。
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            setTranslucentStatus(true)
            val tintManager = SystemBarTintManager(this)
            tintManager.isStatusBarTintEnabled = true
            // 使StatusBarTintView 和 actionbar的颜色保持一致，风格统一。
            if (color != -1) {
                tintManager.setStatusBarTintResource(color)
            } else {
                tintManager.setStatusBarTintResource(R.color.colorAccent)//app主色调
            }
            // 设置状态栏的文字颜色
            tintManager.setStatusBarDarkMode(false, this)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            val window = window
            window.setBackgroundDrawable(null)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE//是否全透明，5.0以上默认半透明
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (color != -1) {
                window.statusBarColor = ContextCompat.getColor(this, color)
            } else {
                val defColor = ContextCompat.getColor(this, R.color.colorAccent)
                window.statusBarColor = defColor//app主色调
            }
        }
    }

    @TargetApi(19)
    private fun setTranslucentStatus(on: Boolean) {
        val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        if (on) {
            window.attributes.flags = window.attributes.flags or bits
        } else {
            window.attributes.flags = window.attributes.flags and bits.inv()
        }
    }

    fun back(v: View) {
        onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.finishAll()
    }
}