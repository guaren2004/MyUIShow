package com.example.robin.coordinatorlayouttest.fragment;

import android.widget.TextView;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.engin.BaseCallback;
import com.example.robin.coordinatorlayouttest.engin.NetManager;
import com.example.robin.coordinatorlayouttest.utils.Util;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 测试 OkHttp 简单封装的使用
 */
public class Fragment3 extends BaseFragment {

    private TextView mTv_3;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_3;
    }

    @Override
    protected void initView() {
        mTv_3 = (TextView) view.findViewById(R.id.tv_3);
    }

    @Override
    protected void initData() {
        final NetManager netManager = NetManager.getInstance();
        final String url = "http://www.baidu.com";

        netManager.get(url, new BaseCallback<String>() {
            @Override
            public void onLinNetBefore() {
                System.out.println(TAG + "onLinNetBefore");
            }

            @Override
            public void onFailure(Call call, Exception e) {
                System.out.println(TAG + "onFailure");
                Util.showToast(getActivity(), "请求失败, 请检查是否连接到网络!");
            }

            @Override
            public void onSuccess(Call call, Response response, String str) {
                System.out.println(TAG + "onSuccess");
                mTv_3.setText(str);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                System.out.println(TAG + "onError");
            }
        });
    }
}
