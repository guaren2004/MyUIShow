package com.example.robin.coordinatorlayouttest.fragment;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.adapter.WaterfallRVAdapter;

/**
 * 测试 SwipeToLoadLayout 用法
 * 以及 封装 OkHttp + RecyclerView 加载图片实现瀑布流
 */
public class Fragment4 extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    private Handler mHandler;
    private String[] imageUrls;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutID() {
        System.out.println(TAG + "getLayoutID");
        return R.layout.fragment_4;
    }

    @Override
    protected void initView() {
        System.out.println(TAG + "initView");
        mSwipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
//        final CustomRefreshHeader header = (CustomRefreshHeader) view.findViewById(R.id.swipe_refresh_header);
//        final SwipeLoadMoreFooterLayout footer = (SwipeLoadMoreFooterLayout) view.findViewById(R.id.swipe_load_more_footer);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);
    }

    @Override
    protected void initData() {
        mHandler = new Handler();
        System.out.println(TAG + "initData");
        initImageUrl();
//        final NetManager netManager = NetManager.getInstance();
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        final WaterfallRVAdapter adapter = new WaterfallRVAdapter(imageUrls, getActivity(), this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        autoRefresh();

//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    Glide.with(Fragment4.this).resumeRequests();
//                } else {
//                    Glide.with(Fragment4.this).pauseRequests();
//                }
//            }
//        });
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // do something
                mSwipeToLoadLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // do something
                mSwipeToLoadLayout.setLoadingMore(false);
            }
        }, 2000);
    }

    private void autoRefresh() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(true);
            }
        });
    }

    private void initImageUrl() {
        imageUrls = new String[]{ // 52 张图片
                "http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037193_1687.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037193_1286.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037192_8379.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037178_9374.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037177_1254.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037177_6203.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037152_6352.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037151_9565.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037151_7904.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037148_7104.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037129_8825.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037128_5291.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037128_3531.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037127_1085.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037095_7515.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037094_8001.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037093_7168.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037091_4950.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949643_6410.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949642_6939.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949630_4505.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949630_4593.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949629_7309.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949629_8247.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949615_1986.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949614_8482.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949614_3743.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949614_4199.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949599_3416.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949599_5269.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949598_7858.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949598_9982.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949578_2770.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949578_8744.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949577_5210.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949577_1998.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949482_8813.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949481_6577.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949480_4490.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949455_6792.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949455_6345.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949442_4553.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949441_8987.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949441_5454.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949454_6367.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949442_4562.jpg"
        };
    }
}
