package com.example.robin.coordinatorlayouttest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * 这里用的是FragmentStatePagerAdapter而不是FragmentPagerAdapter
 * 原因: 如果使用FragmentPagerAdapter, 有时候在调用notifyDataSetChanged的时候不会成功刷新fragment中的数据
 *      所以这里采用了FragmentStatePagerAdapter, 再添加getItemPosition方法, 并返回POSITION_NONE
 *      这样的话, 调用notifySetChanged方法的时候就能成功刷新数据了
 *      返回值:
 *      POSITION_NONE: 每次notifySetChanged都会刷新数据
 *      POSITION_UNCHANGED: 每次notifySetChanged不会做出任何响应
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "MyFragmentPagerAdapter: ";
    private ArrayList<Fragment> list;
    private String[] titles;

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list, String[] titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
        System.out.println(TAG + "MyFragmentPagerAdapter");
    }

    @Override
    public Fragment getItem(int position) {
        System.out.println(TAG + "getItem");
        return list.get(position);
    }

    @Override
    public int getCount() {
        System.out.println(TAG + "getCount");
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        System.out.println(TAG + "getPageTitle");
        return titles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        System.out.println(TAG + "getItemPosition");
        return POSITION_NONE;
    }
}
