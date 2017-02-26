package com.example.robin.coordinatorlayouttest.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected Context context;
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 之前设置的 android:windowBackground 图片还在内存中, 用这个方法去掉
        getWindow().setBackgroundDrawable(null);
        if (context != null) {
            context = null;
        }
    }
}
