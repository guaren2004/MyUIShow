package com.example.robin.coordinatorlayouttest.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.activity.ViewDragHelperActivity;


public class FragmentOne extends BaseFragment {

    private Button mButton;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView() {
        mButton = (Button) view.findViewById(R.id.button);
    }

    @Override
    protected void initData() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ViewDragHelperActivity.class));
                getActivity().finish();
            }
        });
    }
}
