package com.example.robin.coordinatorlayouttest.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * 官方的标准例子
 */
public class CustomRefreshHeader2 extends TextView implements SwipeRefreshTrigger, SwipeTrigger {
    public CustomRefreshHeader2(Context context) {
        super(context);
    }

    public CustomRefreshHeader2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onRefresh() {
        setText("正在刷新...");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled >= getHeight()) {
                setText("释放刷新");
            } else {
                setText("下拉刷新");
            }
        } else {
            setText("刷新返回");
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        setText("完成");
    }

    @Override
    public void onReset() {
        setText("");
    }
}
