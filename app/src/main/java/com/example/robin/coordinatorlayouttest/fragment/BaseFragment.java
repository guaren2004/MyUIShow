package com.example.robin.coordinatorlayouttest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类:
 * 作用: 使 viewPager 中滑动的 Fragment 进行懒加载
 * ---- 懒加载: 当 viewPager 中的 Fragment 同时满足"可见", "已经初始化", "第一次加载" 这三个条件的时候才加载数据
 *
 * 注意:
 * 1. 懒加载一般是在 viewPager + Fragment 滑动模式下应用的
 * 2. 需要判断 view == null 进行 view 的复用
 * 否则在滑动到 viewPager 默认保存的三个 Fragment 状态以外,再返回的时候,视图将无加载,并不可见
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment - ";

    private boolean isVisible = false; // 当前 Fragment 是否可见
    private boolean isInitView = false; // 是否与 View 建立映射关系(是否初始化)
    private boolean isFirstLoad = true; // 是否是第一次加载数据
    protected View view;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadData();
        } else {
            isVisible = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 判断如果 view == null 才初始化各个控件和填充数据,否则直接返回 view
        // 这样做可以复用 view
        /*
        注意: 本例中, viewPager 中的 Fragment(Fragment1 ~  Fragment6) 继承了该基类, 在滑动到 viewPager 默认
        保存的三个 Fragment 状态以外,再返回的时候, view != null ,可以直接呈现之前的视图
        如果没有 view == null 的判断,返回的时候,由于懒加载的原因,视图不会呈现
        所以这里体现了 view 的复用的重要性
         */
        if (view == null) {
            view = inflater.inflate(getLayoutID(), container, false);
            initView();
            isInitView = true;
            lazyLoadData();
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true); // 添加 Fragment 中的菜单
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 在 onDestroyView 的时候将 view 移除
        // 与之前的判断 view == null 形成联动
        final ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
    }

    /**
     * Fragment 懒加载的关键
     * 只要满足"不可见", "没有初始化", "不是第一次加载"中任何一个条件,直接 return ,并不加载空间数据
     * 也就是说只有在 Fragment 同时满足"可见", "已经初始化", "第一次加载" 这三个条件的时候才加载数据
     */
    private void lazyLoadData() {
        if (!isVisible || !isInitView || !isFirstLoad) {
            return;
        }
        initData();
        isFirstLoad = false;
    }

    /**
     * 获取 Fragment 的布局资源ID
     * @return 布局资源ID
     */
    protected abstract int getLayoutID();

    /**
     * 初始化 Fragment 的各个控件( View.findViewById )
     */
    protected abstract void initView();

    /**
     * 用数据填充的各个控件
     */
    protected abstract void initData();
}
