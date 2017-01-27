package com.example.robin.coordinatorlayouttest.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.utils.Util;

/**
 * 重点: Fragment 中设置 Toolbar 的方法
 * ---- ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
 */
public class Fragment11 extends BaseFragment {

    private static final String TAG = "Fragment11 - ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println(TAG + "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_11;
    }

    @Override
    protected void initView() {
        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.tb);
        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tl);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp);

        toolbar.setTitle("Fragment11");
        toolbar.setTitleTextColor(Color.YELLOW);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.showToast(getActivity(), "点击了导航图标");
            }
        });

        tabLayout.setTabTextColors(Color.GRAY, Color.WHITE);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new Fragment1();
                    case 1:
                        return new Fragment2();
                    case 2:
                        return new Fragment3();
                    case 3:
                        return new Fragment4();
                    case 4:
                        return new Fragment5();
                    case 5:
                        return new Fragment6();
                    default:
                        return new Fragment1();
                }
            }

            @Override
            public int getCount() {
                return 6;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "1-fragment";
                    case 1:
                        return "2-fragment";
                    case 2:
                        return "3-fragment";
                    case 3:
                        return "4-fragment";
                    case 4:
                        return "5-fragment";
                    case 5:
                        return "6-fragment";
                    default:
                        return "1-fragment";
                }
            }
        });
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
