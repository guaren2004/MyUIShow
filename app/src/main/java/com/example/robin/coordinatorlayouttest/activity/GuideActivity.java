package com.example.robin.coordinatorlayouttest.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.fragment.FragmentGuide1;
import com.example.robin.coordinatorlayouttest.fragment.FragmentGuide2;
import com.example.robin.coordinatorlayouttest.fragment.FragmentGuide3;

/**
 * 引导页面
 * 重点: viewPager 滑动监听逻辑
 */
public class GuideActivity extends BaseActivity {

    private TextView[] mTextViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }

    private void initView() {
        final FragmentGuide1 fragmentGuide1 = new FragmentGuide1();
        final FragmentGuide2 fragmentGuide2 = new FragmentGuide2();
        final FragmentGuide3 fragmentGuide3 = new FragmentGuide3();

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_guide);
        final TextView tv_guide1 = (TextView) findViewById(R.id.tv_guide1);
        final TextView tv_guide2 = (TextView) findViewById(R.id.tv_guide2);
        final TextView tv_guide3 = (TextView) findViewById(R.id.tv_guide3);

        mTextViews = new TextView[]{tv_guide1, tv_guide2, tv_guide3};

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return fragmentGuide1;
                    case 1:
                        return fragmentGuide2;
                    case 2:
                        return fragmentGuide3;
                    default:
                        return fragmentGuide1;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 关键逻辑: 每次滑到一个页面的时候做一个循环
             * 把当前页面的 position 对应的 i 位置的 TextView 设置成...其余设置成...
             * @param position 当前页面位置
             */
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 3; i++) {
                    if (i == position) {
                        mTextViews[i].setTextColor(Color.RED);
                        mTextViews[i].setTextSize(25);
                    } else {
                        mTextViews[i].setTextColor(Color.WHITE);
                        mTextViews[i].setTextSize(15);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
