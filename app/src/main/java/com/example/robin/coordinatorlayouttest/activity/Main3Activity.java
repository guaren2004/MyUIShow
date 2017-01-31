package com.example.robin.coordinatorlayouttest.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.fragment.FragmentOne;
import com.example.robin.coordinatorlayouttest.fragment.FragmentThree;
import com.example.robin.coordinatorlayouttest.fragment.FragmentTwo;
import com.example.robin.coordinatorlayouttest.utils.Util;


public class Main3Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Util.setTransparentStatusBar(this, null, true);
        initView();
    }

    private void initView() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_img);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new FragmentOne();
                    case 1:
                        return new FragmentTwo();
                    case 2:
                        return new FragmentThree();
                    default:
                        return new FragmentOne();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }
}
