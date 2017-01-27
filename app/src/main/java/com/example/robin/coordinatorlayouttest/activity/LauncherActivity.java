package com.example.robin.coordinatorlayouttest.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

/**
 * 应用的启动/欢迎页面
 * 用以解决应用启动白屏的问题
 * 思路: 应用启动的白色背景其实就是 android:WindowBackground 的默认背景
 * 那么就设置启动的第一个 Activity 的背景图片和这个 Activity 的 android:WindowBackground 的图片一样
 * 而且,如果这个 Activity 布局中也只是一个张和 android:windowBackground 一样的图片,那么 setContentView 方法都可以去掉
 *
 * 注意: Application 中的 theme 也设置了 android:WindowBackground 属性,这里的 Activity 也设置了,就覆盖了
 * 那么 Application 中的 android:WindowBackground 就只能起到设置 NavigationView 背景的作用
 */
public class LauncherActivity extends BaseActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果布局中也只是一个张和 android:windowBackground 一样的图片,那么 setContentView 方法都可以去掉
//        setContentView(R.layout.activity_launcher);

        initView();

    }

    private void initView() {
        final SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        final boolean isFirstStarted = preferences.getBoolean("isFirstStarted", true);
        if (isFirstStarted) { // 如果是第一次启动就跳转到 引导页
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstStarted", false);
            editor.apply();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(context, GuideActivity.class));
                    finish();
                }
            }, 3000);
        } else { // 如果不是第一次启动就跳转到 主页
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
//                    startActivity(new Intent(context, MainActivity.class));
                    startActivity(new Intent(context, GuideActivity.class));
                    finish();
                }
            }, 3000);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // do nothing
        }
        return true;
    }
}
