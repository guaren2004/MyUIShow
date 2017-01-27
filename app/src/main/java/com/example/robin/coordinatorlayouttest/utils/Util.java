package com.example.robin.coordinatorlayouttest.utils;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Field;

public class Util {

    /**
     * 简单显示快速替代 Toast
     */
    private static Toast toast;

    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 获取屏幕宽度 (单位: px)
     */
    public static int getScreenWide(Context context) {
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度 (单位: px)
     */
    public static int getScreenHeight(Context context) {
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight (Context context) {
        int result = -1;
        final int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 设置全透明状态栏
     * @param activity 要设置全透明状态栏的 Activity
     * @param drawerLayout 带有的 DrawerLayout(侧边导航栏开发)的 Activity 传过来的已经初始化(已经 findViewById)
     *                     的 DrawerLayout 对象,如果没有 DrawerLayout ,传入 null
     * @param isFitsSystemWindows 设置当 drawerLayout == null 的时候,根布局是否向下移动一个状态栏
     */
    public static void setTransparentStatusBar(Activity activity, DrawerLayout drawerLayout, boolean isFitsSystemWindows) {
        // 5.0(21) 及以上版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置了这个 flag,drawerLayout 下面的主内容布局的 paddingTop 属性才有用
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // 只加这个flag是半透明效果
            // 下面两个 Flag 一起使用才能使 5.0(21) 及以上版本带有 DrawerLayout 的状态栏完全透明,并且侧边栏也延伸到了状态栏中
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);

            // 4.4(19) 到 5.0(21) 之间的版本
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 方法1:
//            WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
//            layoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutParams.flags);
            // 方法2:
//            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 方法3:
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            if (drawerLayout != null) {
                drawerLayout.setFitsSystemWindows(true); // 将侧边栏顶部延伸至status bar
                drawerLayout.setClipToPadding(false); // 将主页面顶部延伸至 status bar;虽默认为false,但经测试,DrawerLayout需显示设置
            }
        }
        if (drawerLayout == null) {
            // 如果没有 DrawerLayout ,要设置该 activity 的根布局的 fitsSystemWindows="true"
            ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(isFitsSystemWindows);
        }
    }

    /**
     * 设置半透明状态栏(5.0(21)以上才有半透明)
     * @param activity 要设置半透明状态栏的 Activity
     */
    public static void setTranslucentStatusBar(Activity activity, DrawerLayout drawerLayout, boolean isFitsSystemWindows){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            if (drawerLayout == null) {
                // 如果没有 DrawerLayout ,要设置该 activity 的根布局的 fitsSystemWindows="true"
                ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(isFitsSystemWindows);
            }
        }
    }


    public static void setIndicatorWidth(Context context, TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) (getDisplayMetrics(context).density * leftDip);
        int right = (int) (getDisplayMetrics(context).density * rightDip);

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
