package com.example.robin.coordinatorlayouttest.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.fragment.Fragment1;
import com.example.robin.coordinatorlayouttest.fragment.Fragment2;
import com.example.robin.coordinatorlayouttest.fragment.Fragment3;
import com.example.robin.coordinatorlayouttest.fragment.Fragment4;
import com.example.robin.coordinatorlayouttest.utils.Util;

public class NavigationViewActivity extends BaseActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);

        initView();

        // 设置状态栏为透明
        // 状态栏透明一般都与根布局为 DrawerLayout 并带有 fitsSystemWindows="true" 属性配合使用
        // 也可以配合只有图片的布局使用,而且不设置 fitsSystemWindows="true" 达到图片伸入状态栏效果
        Util.setTransparentStatusBar(this, drawerLayout, true);
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_navigation_view);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_nav);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_nav);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager_nav);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);

        // 设置 drawerLayout 中的主内容布局的 paddingTop 属性为状态的高度
        coordinatorLayout.setPadding(0, Util.getStatusBarHeight(context), 0, 0);

        toolbar.setTitle("NavigationViewActivity");
//        toolbar.setNavigationIcon(R.mipmap.ic_launcher); // 第一
        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // 第二
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
        // 使用 ActionBarDrawerToggle 设置导航键,自动实现了一个可变化的图标和自动打开的监听,替代了上面的第一和第二
        final ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState(); // 记得写这一步,如果不写将没有导航图标,不过侧边栏还是可以通过滑动出来

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
                    default:
                        return new Fragment1();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Fra-1";
                    case 1:
                        return "Fra-2";
                    case 2:
                        return "Fra-3";
                    case 3:
                        return "Fra-4";
                    default:
                        return "Fra-1";
                }
            }
        });

        /**
         * setItemIconTintList(ColorStateList tint)
         * -- 如果不写这个方法,所有图标默认都是灰色
         * -- 如果参数写 null ,所有图标都恢复本来应该有的颜色
         * -- 如果写 ColorStateList.valueOf(int color) 可以指定所有图标为某个颜色
         *
         * 如果要修改 item 被选中的颜色参考:"http://blog.csdn.net/u010072711/article/details/51545193"
         *
         * 背景: NavigationView 的背景可在应用主题的 AppTheme 中添加 android:windowBackground 设置
         */
//        navigationView.setItemIconTintList(null); // 设置所有图标都为本来的颜色
//        navigationView.setItemIconTintList(ColorStateList.valueOf(Color.BLUE)); // 设置所有图标都为蓝色

        // 设置 NavigationView 的 item 的文字和图标颜色
        // 重点: 利用 ColorStateList 传递 color 选择器
        final ColorStateList colorStateList = getResources().getColorStateList(R.color.selector);
        navigationView.setItemTextColor(colorStateList);
        navigationView.setItemIconTintList(colorStateList);

        // 设置 NavigationView 的 item 的背景
        // 重点: 必须使用 setItemBackgroundResource 而不是 setItemBackground
        // 可直接传入一个 drawable 选择器
        navigationView.setItemBackgroundResource(R.drawable.item_nav_bg_selector);

        // 设置默认选中项
        navigationView.getMenu().getItem(0).setChecked(true);

        // 设置 NavigationView 中的菜单点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sliding1:
                        Util.showToast(context, "菜单1");
                        return true; // 直接返回,不关闭侧滑菜单
                    case R.id.sliding2:
                        Util.showToast(context, "菜单2");
                        return true; // 直接返回,不关闭侧滑菜单
                    case R.id.sliding3:
                        Util.showToast(context, "菜单3");
                        break;
                    case R.id.action_setting1:
                        Util.showToast(context, "点击了添加");
                        break;
                    case R.id.action_setting2:
                        Util.showToast(context, "点击了删除");
                        break;
                    case R.id.action_setting3:
                        Util.showToast(context, "点击了修改");
                        break;
                    case R.id.action_setting4:
                        Util.showToast(context, "点击了更新所有数据");
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        /**
         * 设置 NavigationView 中的 HeaderLayout 中的控件点击事件
         * 1. navigationView.getHeaderView(0); 获取 headerView
         * 2. 再用 headerView.findViewById 找到其中的控件
         */
        final View headerView = navigationView.getHeaderView(0);
        final ImageView imageView = (ImageView) headerView.findViewById(R.id.img_header);
        final Switch sw = (Switch) headerView.findViewById(R.id.sw_header);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.showToast(context, "图片被点击了");
            }
        });

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Util.showToast(context, "打开");
                } else {
                    Util.showToast(context, "关闭");
                }
            }
        });

        /**
         * 去掉 NavigationView 中的滑动条(ScrollBar)
         * 我们看到的滑动条并不是 NavigationView 的,而是他的 child --> NavigationMenuView 的
         */
        final NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }
    }

    /**
     * 重写点击返回键事件
     * 如果侧滑菜单是打开的,就关闭掉,然后才关闭本 Activity
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
