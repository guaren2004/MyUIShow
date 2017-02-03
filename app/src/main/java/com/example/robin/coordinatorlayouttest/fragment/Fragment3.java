package com.example.robin.coordinatorlayouttest.fragment;

import android.widget.TextView;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.bean.Content;
import com.example.robin.coordinatorlayouttest.bean.User;
import com.example.robin.coordinatorlayouttest.engin.BaseCallback;
import com.example.robin.coordinatorlayouttest.engin.NetManager;

import okhttp3.Call;
import okhttp3.Response;


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
        final Content content = new Content();
        final Content.CoopDataBean coopData = content.getCoopData();
        final Content.CoopDataBean.CreatimeBean creatime = coopData.getCreatime();
        final Content.CoopDataBean.SentimeBean sentime = coopData.getSentime();
        final User user = new User();

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

////        final ArrayMap<String, String> params = new ArrayMap<>();
////        params.put("username", "robin");
////        params.put("password", "123");
//        netManager.post(url, netManager.buildFormRequestBody(null), new BaseCallback() {
//            @Override
//            public void onLinNetBefore() {
//                System.out.println(TAG + "onLinNetBefore");
//            }
//
//            @Override
//            public void onFailure(Call call, Exception e) {
//                System.out.println(TAG + "onFailure");
//            }
//
//            @Override
//            public void onSuccess(Call call, Response response, Object object) {
//                System.out.println(TAG + "onSuccess");
//            }
//
//            @Override
//            public void onError(Call call, Response response, Exception e) {
//                System.out.println(TAG + "onError");
//            }
//        });
    }
}
