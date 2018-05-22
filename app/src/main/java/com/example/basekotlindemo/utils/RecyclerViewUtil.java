package com.example.basekotlindemo.utils;

import android.support.v7.widget.RecyclerView;

/**
 * Created by iCong.
 * Time:2016/12/12-17:35.
 */

public class RecyclerViewUtil {
    /**
     * 判断RecyclerView是否已经滚动到底部
     */
    public static boolean isScrollBottom(RecyclerView recyclerView) {
        return recyclerView.computeVerticalScrollExtent()
                + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange();
    }
}
