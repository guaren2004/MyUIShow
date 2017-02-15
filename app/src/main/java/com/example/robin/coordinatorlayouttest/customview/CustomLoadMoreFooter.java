package com.example.robin.coordinatorlayouttest.customview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * 作者: Robin
 * 时间: 2017/2/5 18:43
 * 邮箱: guaren2005@126.com
 */

public class CustomLoadMoreFooter extends TextView implements SwipeLoadMoreTrigger, SwipeTrigger {
    public CustomLoadMoreFooter(Context context) {
        super(context);
        init();
    }

    public CustomLoadMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setTextColor(Color.YELLOW);
    }

    @Override
    public void onLoadMore() {
        setText("正在加载中...");
    }

    @Override
    public void onPrepare() {

    }

    /**
     * 注意: 这里的 yScrolled 的判断与 CustomRefreshHeader 中的是刚好相反的
     */
    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled > -getHeight()) {
                setText("上拉加载");
            } else {
                setText("释放加载更多");
            }
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        setText("加载完成");
    }

    @Override
    public void onReset() {

    }
}
