package com.example.robin.coordinatorlayouttest.fragment;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.engin.OkHttpClientManager;

import java.io.IOException;

import okhttp3.Response;


public class Fragment3 extends BaseFragment {

    private static final String TAG = "Fragment3: ";

    @Override
    protected int getLayoutID() {
        System.out.println(TAG + "getLayoutID");
        return R.layout.fragment_3;
    }

    @Override
    protected void initView() {
        System.out.println(TAG + "initView");
    }

    @Override
    protected void initData() {
        System.out.println(TAG + "initData");
        try {
            final Response response = OkHttpClientManager.getAsync("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
