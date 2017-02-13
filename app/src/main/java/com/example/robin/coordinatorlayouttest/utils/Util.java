package com.example.robin.coordinatorlayouttest.utils;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

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
     * 是一个比值 = densityDpi / 160, 其中 160 就是 mdpi 标准值
     */
    public static float getScreenDensity(Context context) {
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    /**
     * 就是常说的 dpi: dots per inch (一英寸多少个像素点)
     */
    public static int getScreenDensityDpi(Context context) {
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
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
    public static int getStatusBarHeight(Context context) {
        int result = -1;
        final int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 设置全透明状态栏
     *
     * @param activity            要设置全透明状态栏的 Activity
     * @param drawerLayout        带有的 DrawerLayout(侧边导航栏开发)的 Activity 传过来的已经初始化(已经 findViewById)
     *                            的 DrawerLayout 对象,如果没有 DrawerLayout ,传入 null
     * @param isFitsSystemWindows 设置当 drawerLayout == null 的时候,根布局是否向下移动一个状态栏
     */
    public static void setTransparentStatusBar(Activity activity, DrawerLayout drawerLayout, boolean isFitsSystemWindows) {
        // 5.0(21) 及以上版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置了这个 flag,drawerLayout 下面的主内容布局的 paddingTop 属性才有用
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // 加这个flag是半透明效果,所以要去掉
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
     *
     * @param activity 要设置半透明状态栏的 Activity
     */
    public static void setTranslucentStatusBar(Activity activity, DrawerLayout drawerLayout, boolean isFitsSystemWindows) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            if (drawerLayout == null) {
                // 如果没有 DrawerLayout ,要设置该 activity 的根布局的 fitsSystemWindows="true"
                ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(isFitsSystemWindows);
            }
        }
    }

    /**
     * 缩放 Bitmap 图片
     *
     * @param bitmap    源图片
     * @param newWidth  新宽度
     * @param newHeight 新高度
     * @param recycle   是否回收
     * @return 缩放后的图片
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight, boolean recycle) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        final Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, recycle);
        if (recycle && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return scaledBitmap;
    }

    /**
     * 判断 Bitmap 是否为空
     *
     * @param bitmap 源图片
     */
    private static boolean isEmptyBitmap(Bitmap bitmap) {
        return bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0;
    }
}
