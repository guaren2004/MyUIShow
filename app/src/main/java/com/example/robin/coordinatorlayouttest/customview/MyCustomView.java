package com.example.robin.coordinatorlayouttest.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.robin.coordinatorlayouttest.R;

/**
 * 简单的设置一个自定义属性
 */
public class MyCustomView extends View {
    public MyCustomView(Context context) {
        super(context);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 这里的方法将属性关联到这个自定义view上
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView);
        final int color = typedArray.getColor(R.styleable.MyCustomView_view_bg, Color.YELLOW);
        setBackgroundColor(color);
        typedArray.recycle();
    }
}
