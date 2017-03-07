package com.example.robin.coordinatorlayouttest.fragment;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.robin.coordinatorlayouttest.R;

/**
 * ConstraintLayout 的使用:
 * 需要项目依赖: compile 'com.android.support.constraint:constraint-layout:1.0.1'
 *
 * 以及动态修改的 View 属性:
 * 注意1. 最终是使用 view.setLayoutParams(ViewGroup.LayoutParams params) 方法来完成
 * 注意2. 这个 ViewGroup 是 这个 view 的父布局
 * 注意3. 在 textView1 被 GONE 以后, textView2 继承和保留了哪些 textView1 的属性
 * 步骤:
 * 1: 声明 -- private ConstraintLayout.LayoutParams params;
 * 2: 转化 -- params = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
 * 3: 设置 -- params.horizontalBias += 0.1f;
 * 4: 完成 -- imageView.setLayoutParams(params);
 */
public class FragmentThree extends BaseFragment {

    private ImageView imageView;
    private TextView textView1;
    private TextView textView2;
    private ConstraintLayout.LayoutParams params; // imageView 在 ConstraintLayout 中

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_three;
    }

    @Override
    protected void initView() {
        imageView = (ImageView) view.findViewById(R.id.imageView);
        textView1 = (TextView) view.findViewById(R.id.textView1);
        textView2 = (TextView) view.findViewById(R.id.textView2);
    }

    @Override
    protected void initData() {
        params = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.horizontalBias += 0.1f; // ConstraintLayout 才有的 horizontalBias 属性
                imageView.setLayoutParams(params);
                textView1.setVisibility(View.GONE);
                // 在 textView1 被 GONE 以后, textView2 代替 textView1 约束到了 imageView
                // textView2 其他属性都使用了 textView1 的属性, 但是保留了 marginLeft 的值大小
            }
        });
    }
}
