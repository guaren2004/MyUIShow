package com.example.robin.coordinatorlayouttest.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.activity.MainActivity;

public class FragmentGuide3 extends BaseFragment {

    private Button mBtn_guide3;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_guide_3;
    }

    @Override
    protected void initView() {
        mBtn_guide3 = (Button) view.findViewById(R.id.btn_guide3);
    }

    @Override
    protected void initData() {
        mBtn_guide3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
    }
}
