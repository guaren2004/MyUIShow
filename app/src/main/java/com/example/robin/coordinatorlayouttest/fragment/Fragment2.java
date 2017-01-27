package com.example.robin.coordinatorlayouttest.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.blankj.utilcode.utils.BarUtils;
import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.adapter.RecyclerViewAdapter;
import com.example.robin.coordinatorlayouttest.adapter.RecyclerViewLoadMoreAdapter;
import com.example.robin.coordinatorlayouttest.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 重点: 上拉加载更多
 * -- 1. RecyclerView.addOnScrollListener 中的两个回调方法中可以获取的数据或者可以判断的状态
 * -- 2. RecyclerView 的 Adapter 中的写法
 */
public class Fragment2 extends BaseFragment {

    private Thread mThread;
    private List<String> datas2;
    private List<String> datas3;
    private static final String TAG = "Fragment2: ";
    private RecyclerViewLoadMoreAdapter recyclerViewAdapter2;
    private RecyclerViewAdapter recyclerViewAdapter3;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private SwipeRefreshLayout swipeRefreshLayout2;
    private LinearLayoutManager linearLayoutManager2;
    private int lastVisibleItemPosition;

    @Override
    protected int getLayoutID() {
        System.out.println(TAG + "getLayoutID");
        return R.layout.fragment_2;
    }

    @Override
    protected void initView() {
        System.out.println(TAG + "initView");
        swipeRefreshLayout2 = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout2);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerView2);
        recyclerView3 = (RecyclerView) view.findViewById(R.id.recyclerView3);

        swipeRefreshLayout2.setProgressBackgroundColorSchemeColor(Color.WHITE);
        swipeRefreshLayout2.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        swipeRefreshLayout2.setRefreshing(true);
    }

    @Override
    protected void initData() {
        System.out.println(TAG + "initData");

        initMData();
        initMData2();
        swipeRefreshLayout2.setRefreshing(false);
        System.out.println("执行 initData");
        linearLayoutManager2 = new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        // 复用同一个Adapter,但是加载不同数据
        recyclerViewAdapter2 = new RecyclerViewLoadMoreAdapter(datas2, getActivity());
        recyclerViewAdapter3 = new RecyclerViewAdapter(datas3, getActivity());
        recyclerView2.setAdapter(recyclerViewAdapter2);
        recyclerView3.setAdapter(recyclerViewAdapter3);

        // 添加分割线
        // OrientationHelper.VERTICAL - 设置的是分割线的分割方向,而不是分割线的 Drawable 的方向
        // dividerItemDecoration.setDrawable - 设置分割线的 Drawable, 如果不设置将采用系统默认的风格
        final DividerItemDecoration dividerItemDecoration2 = new DividerItemDecoration(getActivity(), OrientationHelper.VERTICAL);
//        dividerItemDecoration2.setDrawable(ContextCompat.getDrawable(getActivity(), R.mipmap.car));
        recyclerView2.addItemDecoration(dividerItemDecoration2);
        final DividerItemDecoration dividerItemDecoration3 = new DividerItemDecoration(getActivity(), OrientationHelper.HORIZONTAL);
//        dividerItemDecoration3.setDrawable(ContextCompat.getDrawable(getActivity(), R.mipmap.car));
        recyclerView3.addItemDecoration(dividerItemDecoration3);

//        recyclerView2.addItemDecoration(new DividerItemDecoration(getActivity(), OrientationHelper.VERTICAL));
//        recyclerView3.addItemDecoration(new DividerItemDecoration(getActivity(), OrientationHelper.HORIZONTAL));

        // 调用自定义接口提供的公共方法--new出接口对象--实现接口的方法--接受接口的方法的参数--处理
        recyclerViewAdapter2.setOnRecyclerViewItemClickListener(new RecyclerViewLoadMoreAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (view.getTag() == "ItemView") {
                    Snackbar.make(view, datas2.get(position), Snackbar.LENGTH_LONG).show();
                } else if (view.getTag() == "TextView") {
                    Util.showToast(getActivity(), "点击了text: " + " -在集合中的位置: " + position);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                recyclerViewAdapter2.removeItem(position);
            }
        });

        recyclerViewAdapter3.setOnRecyclerViewItemClickListener(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (view.getTag() == "ItemView") {
                    Snackbar.make(view, datas3.get(position), Snackbar.LENGTH_LONG).show();
                } else if (view.getTag() == "TextView") {
                    Util.showToast(getActivity(), "点击了text: " + " -在集合中的位置: " + position);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                recyclerViewAdapter3.removeItem(position);
            }
        });

        /**
         * 下拉刷新
         */
//        swipeRefreshLayout2.setProgressBackgroundColorSchemeColor(Color.WHITE);
//        swipeRefreshLayout2.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        swipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datas2.clear();
                        initNewData();
                        recyclerViewAdapter2.notifyDataSetChanged();
                        swipeRefreshLayout2.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        /**
         * 上拉加载更多
         */
        recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /*
            滑动状态改变的时候回调
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // 如果 RecyclerView 现在处于没有滑动状态 并且 是最后一个 item
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == recyclerViewAdapter2.getItemCount()) {
                    recyclerViewAdapter2.changeLoadMoreStatus(RecyclerViewLoadMoreAdapter.LOADING_MORE); // 状态改变成"加载中..."
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final List<String> addedNewData = addedNewData();
                            if (addedNewData != null && addedNewData.size() > 0) {
                                recyclerViewAdapter2.addMoreItem(addedNewData); // 添加入新数据
                                recyclerViewAdapter2.changeLoadMoreStatus(RecyclerViewLoadMoreAdapter.PULL_TO_LOAD_MORE); // 状态改回"上拉加载更多"
                            } else {
                                recyclerViewAdapter2.changeLoadMoreStatus(RecyclerViewLoadMoreAdapter.NO_MORE_DATA); // 状态改回"没有更多数据"
                            }
                        }
                    }, 3000);
                }
            }

            /*
            滑动的时候回调
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 使用 LinearLayoutManager 获取最后一个课件 item 的 index 下标
                lastVisibleItemPosition = linearLayoutManager2.findLastVisibleItemPosition();
            }
        });
    }

    private List<String> addedNewData() {
        final List<String> addedNewData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            addedNewData.add("moreItem " + i);
        }
        return addedNewData;
    }

    private void initNewData() {
        for (int i = 0; i < 12; i++) {
            datas2.add("new item " + i);
        }
    }

    private void initMData() {
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("initMData开始执行 - " + Thread.currentThread());
                datas2 = new ArrayList<>();
                for (int i = 0; i < 17; i++) {
                    datas2.add("item" + i);
                }
                System.out.println("initMData结束执行 - " + Thread.currentThread());
            }
        });
        mThread.start();
        try {
            mThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("initMData - " + "join");
    }

    private void initMData2() {
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("initMData2开始执行 - " + Thread.currentThread());
                datas3 = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    datas3.add("hItem" + i);
                }
                System.out.println("initMData2结束执行 - " + Thread.currentThread());
            }
        });
        mThread.start();
        try {
            mThread.join();
            System.out.println("initMData2 - " + "join");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main2, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting1:
//                Util.showToast(getActivity(), TAG + "action_setting1");
                BarUtils.showNotificationBar(getActivity(), false);
                break;
            case R.id.action_setting2:
                // 利用 getActivity() 找到 Activity 中的控件,然后进行操作
                final DrawerLayout drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawerLayout);
                if (drawerLayout != null) {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
                break;
            case R.id.action_setting3:
                Util.showToast(getActivity(), TAG + "action_setting3");
                break;
        }
        return true;
    }
}
