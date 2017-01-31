package com.example.robin.coordinatorlayouttest.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.fragment.Fragment11;
import com.example.robin.coordinatorlayouttest.fragment.Fragment22;
import com.example.robin.coordinatorlayouttest.fragment.Fragment33;
import com.example.robin.coordinatorlayouttest.fragment.Fragment44;
import com.example.robin.coordinatorlayouttest.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 带底部导航栏的页面
 * BottomNavigationView 开发
 */
public class BottomActivity extends BaseActivity {

    private FragmentManager fragmentManager;
    private Fragment currentFragment = null;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        Util.setTransparentStatusBar(this, null, true);
        addFragment();
        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            initFragment("fragment_11", 0);
        }
        initView();
    }

    private void addFragment() {
        fragmentList.add(new Fragment11());
        fragmentList.add(new Fragment22());
        fragmentList.add(new Fragment33());
        fragmentList.add(new Fragment44());
    }

    private void initView() {
        final BottomNavigationView bottomView = (BottomNavigationView) findViewById(R.id.bottomView);
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        Util.showToast(context, "首页");
                        initFragment("fragment_11", 0);
                        break;
                    case R.id.discovery:
                        Util.showToast(context, "发现");
                        initFragment("fragment_22", 1);
                        break;
                    case R.id.msg:
                        Util.showToast(context, "消息");
                        initFragment("fragment_33", 2);
                        break;
                    case R.id.mine:
                        Util.showToast(context, "我的");
                        initFragment("fragment_44", 3);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 按照一套逻辑 add 或者 show 或者 hide Fragment
     * @param tag 添加 Fragment 的时候传入的 tag , 用于 fragmentManager.findFragmentByTag(tag)
     * @param index 存储 Fragment 的集合中对应的下标
     */
    private void initFragment(String tag, int index) {
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) { // 说明第一次添加这个 Fragment
            fragment = fragmentList.get(index);
            if (currentFragment == null) { // 说明容器之前未添加任何 Fragment
                fragmentManager.beginTransaction() // 直接 add 这个 Fragment
                        .add(R.id.fl_container, fragment, tag)
                        .commit();
            } else { // 说明之前添加过别的 Fragment
                fragmentManager.beginTransaction() // 先 hide 当前的 Fragment 再 add 这个 Fragment
                        .hide(currentFragment)
                        .add(R.id.fl_container, fragment, tag)
                        .commit();
            }
        } else if (fragment.isHidden()) { // 说明之前被添加过,并且现在处于隐藏状态
            fragmentManager.beginTransaction() // hide 当前的 Fragment 再 show 这个 Fragment
                    .hide(currentFragment)
                    .show(fragment)
                    .commit();
        }
        // 上面两个条件都不满足,说明既不是第一次添加,又没有在隐藏状态,说明就是当前正在显示的 fragment
        // 所以可以什么都不干,直接设置成当前的 currentFragment
        // 最后无论是 add 还是 show 了这个 fragment , 设置成当前的 currentFragment
        currentFragment = fragment;
    }
}
