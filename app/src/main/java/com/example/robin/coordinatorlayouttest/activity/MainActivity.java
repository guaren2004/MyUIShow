package com.example.robin.coordinatorlayouttest.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.fragment.Fragment1;
import com.example.robin.coordinatorlayouttest.fragment.Fragment2;
import com.example.robin.coordinatorlayouttest.fragment.Fragment3;
import com.example.robin.coordinatorlayouttest.fragment.Fragment4;
import com.example.robin.coordinatorlayouttest.fragment.Fragment5;
import com.example.robin.coordinatorlayouttest.fragment.Fragment6;
import com.example.robin.coordinatorlayouttest.utils.Util;


public class MainActivity extends BaseActivity {

    private long time = 0;
//    private ArrayList<Fragment> list;
//    private String[] titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏状态栏,要在 setContentView 之前调用
//        BarUtils.hideStatusBar(this);
        setContentView(R.layout.activity_main2);

//        list = new ArrayList<>();
//        list.add(new Fragment1());
//        list.add(new Fragment2());
//        list.add(new Fragment3());
//        list.add(new Fragment4());
//        list.add(new Fragment5());
//        list.add(new Fragment6());
//        titles = new String[]{"1-fragment", "2-fragment", "3-fragment", "4-fragment", "5-fragment", "6-fragment"};

        Util.setTransparentStatusBar(this, null, true);
        initView();
    }

    private void initView() {
        final Toolbar toolBar = (Toolbar) findViewById(R.id.toolBar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final TextView tv_right = (TextView) findViewById(R.id.tv_right);
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage("是否跳转到 SlidingActivity ?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing ...
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(MainActivity.this, SlidingActivity.class));
                            }
                        })
                        .show();
            }
        });

        toolBar.setNavigationIcon(android.R.drawable.ic_lock_idle_lock); // 设置导航图标
//        toolBar.setLogo(android.R.drawable.ic_dialog_map);
//        toolBar.setTitle("  Title");
//        toolBar.setTitleTextColor(Color.YELLOW);
        // 关键代码: 用Toolbar代替系统ActionBar,并注意Styles.xml里面的设置
        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage("是否跳转到 NavigationViewActivity ?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing ...
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(MainActivity.this, NavigationViewActivity.class));
                            }
                        })
                        .show();
            }
        });
        // collapsingToolbarLayout设置最好在setSupportActionBar(toolBar);之后
        // 当有collapsingToolbarLayout的时候要用collapsingToolbarLayout设置Title,用Toolbar设置Title不会显示
        collapsingToolbarLayout.setTitle("MainActivity");
        // 设置展开(Expanded)后的Title颜色和折叠(Collapsed)后的Title颜色,过渡动画已经自动实现
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(context, android.R.color.holo_blue_bright));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.YELLOW);

//        collapsingToolbarLayout.setTitleEnabled(false); // 表示collapsingToolbarLayout 不设置title,这个时候可以用toolBar来设置,title不会跟着滑动

//        final MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list, titles);
//        viewPager.setAdapter(adapter);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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

//            @Override
//            public int getItemPosition(Object object) {
//                return POSITION_NONE;
//            }
        });

        tabLayout.setupWithViewPager(viewPager); // TabLayout关联ViewPager

        fab.setOnClickListener(new View.OnClickListener() { // FloatingActionButton点击事件

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "跳转到 BottomActivity ?", Snackbar.LENGTH_LONG)
                        .setAction("确定!", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final Intent intent = new Intent(context, BottomActivity.class);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });
    }


    // ########################################### Menu ###########################################
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        final int itemId = item.getItemId();
//        if (itemId == R.id.action_setting1) {
//            Util.showToast(context, "activity - " + "action_setting1");
//        } else if (itemId == R.id.action_setting2) {
//            Util.showToast(context, "activity - " + "action_setting2");
//        } else if (itemId == R.id.action_setting3) {
//            Util.showToast(context, "activity - " + "action_setting3");
//        }
//        return true;
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - time > 2000) {
                time = System.currentTimeMillis();
                Util.showToast(context, "再按一次退出");
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
