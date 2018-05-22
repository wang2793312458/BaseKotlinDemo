package com.example.basekotlindemo

import android.os.Bundle
import android.view.View
import com.example.basekotlindemo.common.BaseActivity
import com.example.basekotlindemo.http.HttpFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {
    override fun getContentViewLayoutId() = R.layout.activity_main
    override fun initView(savedInstanceState: Bundle?) {
        btn1.onClick {
            showLoading()
        }
        btn2.onClick {
            showNetError(View.OnClickListener {
                toast("再来一次")
            })
        }
        btn3.onClick {
            showEmpty(View.OnClickListener {
                toast("再来一次")
            })
        }
        btn4.onClick {
            showContent()
        }
        btn5.onClick {
            HttpFactory.getInstance()
                    .getCode(mapOf("" to ""))
                    .compose(HttpFactory.schedulers())
                    .doOnSubscribe { showLoading() }
                    .doFinally { showContent() }
                    .subscribe({
                        if (it) {
                            toast(R.string.tips_get_success)
                        } else {
                            toast(R.string.tips_get_failed)
                        }
                    }, {
                        toast(it.message!!)
                    })

        }
    }
}
