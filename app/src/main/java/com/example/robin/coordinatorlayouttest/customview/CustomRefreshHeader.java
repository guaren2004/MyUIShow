package com.example.robin.coordinatorlayouttest.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.example.robin.coordinatorlayouttest.R;

/**
 * 作者: Robin
 * 时间: 2017/2/5 18:43
 * 邮箱: guaren2005@126.com
 */

public class CustomRefreshHeader extends LinearLayout implements SwipeRefreshTrigger, SwipeTrigger {

    private ProgressBar progressBar;
    private TextView textFooter;

    public CustomRefreshHeader(Context context) {
        super(context);
        init();
    }

    public CustomRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        /**
         * 注意:
         * 1. addView 本身可以只要一个 View 参数, 但是这里必须加上 LayoutParams 参数, 否则下拉刷新的布局是一片白色
         * 2. 在 R.layout.header 中, Progressbar 首先就设置成了 GONE, TextView 也首先设置成了 center
         * 因为不知道什么原因, 在第一次下拉之后, Progressbar 的 GONE 属性的"不占据空间"就不起作用了
         * 如果首先就在 R.layout.header 中设置了的话, 就没问题
         */
        final ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final View view = View.inflate(getContext(), R.layout.header, null);
        addView(view, params);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        textFooter = (TextView) view.findViewById(R.id.textFooter);
    }

    @Override
    public void onRefresh() {
        progressBar.setVisibility(View.VISIBLE);
        textFooter.setText("加载中...");
    }

    @Override
    public void onPrepare() {
    }

    /**
     * 移动的时候回调
     *
     * @param i 偏移距离: 拉出来的距离
     */
    @Override
    public void onMove(int i, boolean b, boolean b1) {
        if (i <= getHeight()) {
            progressBar.setVisibility(View.GONE);
            textFooter.setText("下拉刷新");
        } else {
            progressBar.setVisibility(View.GONE);
            textFooter.setText("释放加载更多");
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        progressBar.setVisibility(View.GONE);
        textFooter.setText("加载完成");
    }

    @Override
    public void onReset() {

    }
}
