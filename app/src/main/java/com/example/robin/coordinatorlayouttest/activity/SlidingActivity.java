package com.example.robin.coordinatorlayouttest.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.fragment.Fragment1;
import com.example.robin.coordinatorlayouttest.fragment.Fragment2;
import com.example.robin.coordinatorlayouttest.fragment.Fragment3;
import com.example.robin.coordinatorlayouttest.fragment.Fragment4;
import com.example.robin.coordinatorlayouttest.fragment.Fragment5;
import com.example.robin.coordinatorlayouttest.utils.Util;

public class SlidingActivity extends BaseActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding);

        initView();
        // 设置状态栏为透明
        // 状态栏透明一般都与根布局为 DrawerLayout 并带有 fitsSystemWindows="true" 属性配合使用
        // 也可以配合只有图片的布局使用,而且不设置 fitsSystemWindows="true" 达到图片伸入状态栏效果
        Util.setTransparentStatusBar(this, drawerLayout, true);
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sliding);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_sliding);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager_sliding);
        final TextView tv_left1 = (TextView) findViewById(R.id.tv_left1);

        // 设置 drawerLayout 中的主内容布局的 paddingTop 属性为状态的高度
        coordinatorLayout.setPadding(0, Util.getStatusBarHeight(context), 0, 0);

        toolbar.setTitle("SlidingActivity");
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这里参数可以用 Gravity.LEFT, 但是推荐使用 GravityCompat.START
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        tabLayout.setupWithViewPager(viewPager);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new Fragment1();
                    case 1:
                        return new Fragment2();
                    case 2:
                        return new Fragment3();
                    case 3:
                        return new Fragment4();
                    case 4:
                        return new Fragment5();
                    default:
                        return new Fragment1();
                }
            }

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "F-1";
                    case 1:
                        return "F-2";
                    case 2:
                        return "F-3";
                    case 3:
                        return "F-4";
                    case 4:
                        return "F-5";
                    default:
                        return "F-6";
                }
            }
        });

        tv_left1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.showToast(context, "点击了tv_left1");
                drawerLayout.closeDrawer(GravityCompat.START); // 点击事件后可以选择是否关闭侧边栏
            }
        });
    }

    /**
     * 按返回键如果侧边栏是打开的,就关闭侧边栏,否则再退出
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}
