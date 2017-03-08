package com.example.robin.coordinatorlayouttest.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.robin.coordinatorlayouttest.R;

/**
 * 自定义view
 * 一个圆形, 并且可以自定义半径
 */
public class MyCircleView extends View {

    private float radius;

    public MyCircleView(Context context) {
        super(context);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 这里的方法将 attrs.xml 中定义的属性关联到这个自定义view
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCircleView);
        radius = typedArray.getDimensionPixelSize(R.styleable.MyCircleView_view_radius, 50);
        final int color = typedArray.getColor(R.styleable.MyCircleView_view_bg, Color.RED);
        setBackgroundColor(color);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(radius, radius, radius, paint); // 画布自带的画圆形图的方法
    }
}
