package com.example.robin.coordinatorlayouttest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robin.coordinatorlayouttest.R;

public class Fragment33 extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println(TAG + "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_33;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroyView() {
        System.out.println(TAG + "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        System.out.println(TAG + "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println(TAG + "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        System.out.println(TAG + "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        System.out.println(TAG + "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        System.out.println(TAG + "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        System.out.println(TAG + "onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        System.out.println(TAG + "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        System.out.println(TAG + "onDetach");
        super.onDetach();
    }
}
