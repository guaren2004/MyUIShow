package com.example.robin.coordinatorlayouttest.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.activity.Main3Activity;
import com.example.robin.coordinatorlayouttest.adapter.RecyclerViewAdapter;
import com.example.robin.coordinatorlayouttest.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 重点1. setLayoutManager(RecyclerView.LayoutManager layout)
 * 其中的RecyclerView.LayoutManager一般写三种:
 * 1. new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
 * -- 如果第二个参数是StaggeredGridLayoutManager.VERTICAL(垂直方向),那第一个参数代表列数
 * -- 如果第二个参数是StaggeredGridLayoutManager.HORIZONTAL(水平方向),那第一参数代表行数
 * -- 注意: 如果要写瀑布流,定义 CardView(也就是 RecyclerView 的 item)高度为 wrap_content
 * 2. new LinearLayoutManager(Context context)
 * 3. new GridLayoutManager(Context context, int spanCount): 用的最少,几乎被 StaggeredGridLayoutManager 取代
 *
 *
 * 重点2. Fragment菜单
 * 使用场景: Activity设置了Toolbar的菜单,在滑动到ViewPager中的某一个Fragment的时候
 * 想利用Toolbar的菜单对当前页面的Fragment中的RecyclerView或者别的View的数据进行增删查改
 * 解决方法:
 * 1. Activity中的Toolbar照常设置
 * -- 但是,Activity中的 onCreateOptionsMenu 和 onOptionsItemSelected 方法不能写出来
 * -- 如果这两个方法写出来,会对Fragment中的菜单造成覆盖
 * 2. 在Fragment中写入 setHasOptionsMenu(true); 的方法来启用Fragment的菜单
 * -- 建议在Fragment的 onActivityCreated 方法中启用(本例写在了BaseFragment中)
 * -- 注意: 即使多个Fragment中都启用了该方法,在ViewPager滑动的时候
 * -- 位于Toolbar位置的菜单也不会跟着Fragment滑动,因为Toolbar是在Activity中设置并初始化
 * 3. 在Fragment中写入 onCreateOptionsMenu 和 onOptionsItemSelected
 * -- 注意: Fragment中的onCreateOptionsMenu方法和Activity中的onCreateOptionsMenu方法有些许区别
 * -- 而且每个Fragment可以在onCreateOptionsMenu中inflate不同的菜单布局
 *
 *
 * 重点3. RecyclerView增删动画
 * 方法1. adapter.notifyDataSetChanged(): 数遍变化刷新界面(无动画效果)
 * -- 这个方法一般用于修改了Fragment中的datas后,直接在Fragment中调用
 * -- 然后会重新RecyclerView.setAdapter --> 初始化adapter中的datas数据,重新显示整个界面
 * -- 这个方法适合在Fragment中直接调用
 * 方法2. notifyItemInserted(int position): (默认动画)
 * -- 这个方法告诉adapter在指定位置插入了数据,至于具体数据是什么,则需要设置adapter中的datas
 * -- 这个方法如果在Fragment中调用会比较麻烦,需要先设置Fragment中的datas,再设置给adapter的datas
 * -- 再调用的adapter.notifyItemInserted
 * -- 推荐: 在adapter中封装一个方法,方法中修改adapter中的datas再调用notifyItemInserted
 * -- 比如在adapter中这样封装一个方法供Fragment调用
 * public void addItem(int position, String text){
 * datas.add(position, text);
 * notifyItemInserted(position);
 * }
 * 注意:
 * -- 这样封装修改adapter中的datas并调用notifyItemInserted方法后
 * -- Fragment中的datas也会跟着改变,因为adapter内部采用了一个观察者模式
 * 方法3. notifyItemRemoved(int position): (默认动画)
 * -- 推荐: 在adapter中封装一个方法,方法中修改adapter中的datas再调用notifyItemRemoved
 * public void removeItem(int position){
 * datas.remove(position);
 * notifyItemRemoved(position);
 * }
 * 方法4. notifyItemChanged(int position): (默认动画)
 * -- 推荐: 在adapter中封装一个方法,方法中修改adapter中的datas再调用notifyItemChanged
 * public void changeItem(int position, String text){
 * datas.set(position, text);
 * notifyItemChanged(position);
 * }
 *
 * RecyclerView更新UI的高级方法:
 * notifyItemChanged(int position) 更新列表position位置上的数据可以调用
 * notifyItemInserted(int position) 列表position位置添加一条数据时可以调用,伴有动画效果
 * notifyItemRemoved(int position) 列表position位置移除一条数据时调用，伴有动画效果
 * notifyItemMoved(int fromPosition, int toPosition) 列表fromPosition位置的数据移到toPosition位置时调用,伴有动画效果
 * notifyItemRangeChanged(int positionStart, int itemCount) 列表从positionStart位置到itemCount数量的列表项进行数据刷新
 * notifyItemRangeInserted(int positionStart, int itemCount) 列表从positionStart位置到itemCount数量的列表项批量添加数据时调用,伴有动画效果
 * notifyItemRangeRemoved(int positionStart, int itemCount) 列表从positionStart位置到itemCount数量的列表项批量删除数据时调用,伴有动画效果
 *
 * 重点4. BaseFragment 配合 ViewPager + Fragment 实现懒加载
 *
 * 重点5. 使用 SwipeRefreshLayout 包裹 RecyclerView 实现下拉刷新
 */
public class Fragment1 extends BaseFragment {

    private List<String> datas;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected int getLayoutID() {
        System.out.println(TAG + "getLayoutID");
        return R.layout.fragment_1;
    }

    @Override
    protected void initView() {
        System.out.println(TAG + "initView");
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        System.out.println(TAG + "initData");

        initMData();

        /**
         * 前提: 在 RecyclerView 的 item 也就是 CardView 的高度没有设置成固定值,而是设置成了 wrap_content 的情况下
         * LayoutManager 如果设置成
         * -- StaggeredGridLayoutManager: 交错格子布局,每个 item 的高度都是独立的,然后如果 item 包裹着不同高度的图片或者内容,就形成了瀑布流
         * -- GridLayoutManager: 格子布局,每一行中的所有 item 的高度是一样的,而且这个高度 = 这一行中高度最高的那个 item 的高度
         */
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerViewAdapter = new RecyclerViewAdapter(datas, getActivity());
        mRecyclerView.setAdapter(recyclerViewAdapter);

        // RecyclerView 的尺寸在每次改变时,setHasFixedSize 的作用就是确保尺寸是通过用户输入从而确保 RecyclerView 的尺寸是一个常数
        // RecyclerView 的 Item 宽或者高不会变.每一个 Item 添加或者删除都不会变.
        // 如果你没有设置 setHasFixedSized 代价将会是非常昂贵的.因为 RecyclerView 会需要额外计算每个 item 的 size
        mRecyclerView.setHasFixedSize(true);

        // 添加分割线
        // OrientationHelper.VERTICAL - 设置的是分割线的分割方向,而不是分割线的 Drawable 的方向
        // dividerItemDecoration.setDrawable - 设置分割线的 Drawable, 如果不设置将采用系统默认的风格
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), OrientationHelper.VERTICAL);
//        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.mipmap.ic_launcher));
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        // 调用自定义接口提供的公共方法--new出接口对象--实现接口的方法--接受接口的方法的参数--处理
        // textVIew和view用的同一个回调接口,但是textView和view设置了不一样的tag,在这里可以判断加以区别
        recyclerViewAdapter.setOnRecyclerViewItemClickListener(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (view.getTag() == "ItemView") {
                    Snackbar.make(view, datas.get(position), Snackbar.LENGTH_LONG).show();
                } else if (view.getTag() == "TextView" && position == 2) {
                    startActivity(new Intent(getActivity(), Main3Activity.class));
                } else if (view.getTag() == "TextView") {
                    Util.showToast(getActivity(), "点击了text: " + " -在集合中的位置: " + position);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                recyclerViewAdapter.removeItem(position);
            }
        });

        // 下拉刷新
        // 设置进度条的颜色变化: 设置一个颜色的就一直保持这个颜色,设置多个就每转一圈(一秒)变化一个颜色
//        mSwipeRefreshLayout.setColorSchemeColors(Color.YELLOW, Color.BLUE, Color.BLACK, Color.GREEN, Color.GRAY);
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorAccent));

        // 设置进度条背景颜色
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));

        // 设置下拉刷新监听事件
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 模拟读取网络数据
                // 注意: new Handler().postDelayed(new Runnable() {run(){...}}, int time);
                // run 里面的方法是在主线程执行,延迟 int time 的时间以后再一并执行 run 里面的代码
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("查看线程");
                        datas.clear();
                        initNewData();
                        recyclerViewAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false); // 刷新 UI 数据后 关闭进度条
                    }
                }, 3000);
            }
        });
    }

    private void initMData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            if (i == 0) {
                datas.add("屏幕宽度(px): " + Util.getScreenWide(getActivity()));
            } else if (i == 1) {
                datas.add("屏幕高度(px): " + Util.getScreenHeight(getActivity()));
            } else if (i == 2) {
                datas.add("跳转到 Main3Activity");
            } else {
                datas.add("item" + i);
            }
        }
    }

    private void initNewData() {
        for (int i = 0; i < 20; i++) {
            datas.add("newItem" + i);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting1: // 增加
                // 如果在Fragment中调用该方法,则需要以下三行代码,手动修改Fragment和adapter中的datas
//                datas.add(1, "添加项");
//                recyclerViewAdapter.setDatas(datas);
//                recyclerViewAdapter.notifyItemInserted(1);
                // 封装在adapter中后调用,直接刷新界面,并且Fragment中的datas和adapter中的datas自动刷新
                recyclerViewAdapter.addItem(1, "增加项");
                break;
            case R.id.action_setting2: // 删除
//                datas.remove(1);
//                recyclerViewAdapter.setDatas(datas);
//                recyclerViewAdapter.notifyItemRemoved(1);
                recyclerViewAdapter.removeItem(1);
                break;
            case R.id.action_setting3: // 修改
                recyclerViewAdapter.changeItem(1, "lalala");
                break;
            case R.id.action_setting4: // 更新所有数据
                // 重点: 在原数据集上更新你的数据,而不是用一个新的数据集替代
                // 所以: 先清空datas, 然后重新 add 每条数据,或者datas.addAll(List<String> newDatas)
                // 总之: 要在原数据(datas)基础上去更新数据
                datas.clear();
                initNewData();
                recyclerViewAdapter.notifyDataSetChanged();
                break;
        }
        return true;
    }
}
