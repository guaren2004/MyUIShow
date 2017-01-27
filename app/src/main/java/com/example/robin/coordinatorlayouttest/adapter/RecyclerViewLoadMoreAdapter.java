package com.example.robin.coordinatorlayouttest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.robin.coordinatorlayouttest.R;

import java.util.List;

/**
 * 添加了下拉加载更多的 FooterView 的 RecyclerView.Adapter
 *
 * 重点:
 * 1. 在 onCreateViewHolder 方法中 inflate 子项的布局
 * 2. 回调接口的定义, 以及 ViewHolder 的定义
 * 3. itemView 以及 itemView 中的组件的点击事件在 onBindViewHolder 中的定义
 * onBindViewHolder 在 RecyclerView 滑动的时候反复回调
 * 4. 设置点击事件的时候使用到的 holder.getLayoutPosition() 方法
 * 5. 复用同一个回调接口的时候,加上不同的 Tag ,加以区别
 * 6. 为了实现上拉加载更多,需要添加的代码
 */
public class RecyclerViewLoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0; // 普通 item view
    private static final int TYPE_FOOTER = 1; // 底部 footer view

    private int load_status = 0; // 默认 0 (上拉加载更多)
    public static final int PULL_TO_LOAD_MORE = 0; // 上拉加载更多
    public static final int LOADING_MORE = 1; // 正在加载中
    public static final int NO_MORE_DATA = 2; // 没有更多数据

    private List<String> datas;
    private Context context;

    // 设置构造函数中的List数据和context传递数据到adapter
    public RecyclerViewLoadMoreAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    // ############################# 利用接口传递数据(view)的小方法 ################################
    // 定义一个接口,接口中必定有个带有参数的方法,这个参数就是要传递的数据
    public interface OnRecyclerViewItemClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    // 维护一个接口对象,方便调用接口的方法
    private OnRecyclerViewItemClickListener listener;

    // 设置一个公共方法(可以是接口的get set方法,用于被Fragment调用)
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    // 不一定要设置成的get set方法,自己定义这个公共方法的名字也可以,如下:
    //    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
//        this.listener = listener;
//    }
    // ############################# 利用接口传递数据(view)的小方法 ################################

    /**
     * 根据 viewType 加载不同布局的 item
     * 即根据 public int getItemViewType(int position) 方法返回的 int viewType 判断
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        final View view = layoutInflater.inflate(R.layout.cardview_item, parent, false);
//        final ViewHolder viewHolder = new ViewHolder(view);
        if (viewType == TYPE_ITEM) {
            final View itemView = LayoutInflater.from(context).inflate(R.layout.cardview_item2, parent, false);
            return new ItemViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            final View footerView = LayoutInflater.from(context).inflate(R.layout.cardview_footer, parent, false);
            return new FooterViewHolder(footerView);
        }
        return null;
    }

    /**
     * 在这里设置点击事件,并利用接口回调传递给调用者
     * 注意: 这里使用了 holder.getLayoutPosition() 方法
     * 在 item 是具备变化可能的话不要直接使用 position 参数
     * getLayoutPosition - 涉及到本布局的 item position 的计算的时候最好使用
     * getAdapterPosition - 涉及到外部方法调用的适配器位置项
     * <p>
     * final ViewHolder mHolder = (ViewHolder) holder;
     * holder 可以转化为自己定义的 ViewHolder
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 可以在这里给itemView设置Tag和Id
        // view设置一个tag, tv设置一个tag, 用于在点击事件的时候区别
        // 并且可以设置点击事件, 都使用的同一个回调接口, 用不同的tag区别
        if (holder instanceof ItemViewHolder) { // 如果 onCreateViewHolder 方法返回的是 ItemViewHolder 实例
            final ItemViewHolder mHolder = (ItemViewHolder) holder;
            mHolder.view.setTag("ItemView");
            mHolder.tv.setTag("TextView");
            mHolder.tv.setText(datas.get(position));

            if (listener != null) {
                mHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int layoutPosition = mHolder.getLayoutPosition(); // getLayoutPosition
                        listener.onItemClick(mHolder.view, layoutPosition);
                    }
                });

                mHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        final int layoutPosition = mHolder.getLayoutPosition(); // getLayoutPosition
                        listener.onItemLongClick(mHolder.view, layoutPosition);
                        return true;
                    }
                });

                mHolder.tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(mHolder.tv, mHolder.getLayoutPosition());
                    }
                });
            }
        } else if (holder instanceof FooterViewHolder) { // 如果 onCreateViewHolder 方法返回的是 FooterViewHolder 实例
            final FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            switch (load_status) {
                case PULL_TO_LOAD_MORE: // 取消掉 ProgressBar,居中显示"上拉加载更多"
                    footerViewHolder.progressBar.setVisibility(View.GONE);
                    footerViewHolder.textFooter.setText("上拉加载更多");
                    footerViewHolder.textFooter.setGravity(Gravity.CENTER);
                    break;
                case LOADING_MORE: // 同时显示 ProgressBar 与 TextView
                    footerViewHolder.progressBar.setVisibility(View.VISIBLE);
                    footerViewHolder.textFooter.setText("加载中...");
                    footerViewHolder.textFooter.setGravity(Gravity.START);
                    break;
                case NO_MORE_DATA: // 取消掉 ProgressBar,居中显示"没有更多数据"
                    footerViewHolder.progressBar.setVisibility(View.GONE);
                    footerViewHolder.textFooter.setText("没有更多数据");
                    footerViewHolder.textFooter.setGravity(Gravity.CENTER);
                    break;
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个 item 设置为 footerView
        // 由于 getItemCount() 中加了1,所以这个最后一个 item 就是预留给 footerView 的
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        // 返回的 item 数量在数据的基础上面+1,增加一项 footerView 布局项
        return datas.size() + 1;
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        private View view; // 后面设置view = itemView;用于把itemView传递出去以调用
        private TextView tv;

        ItemViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tv = (TextView) itemView.findViewById(R.id.text);
        }
    }

    private static class FooterViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;
        private TextView textFooter;

        FooterViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            textFooter = (TextView) itemView.findViewById(R.id.textFooter);
        }
    }

    // ######################### 定义增加,删除,修改方法以被Fragment调用 ###########################
    public void addItem(int position, String text) {
        datas.add(position, text);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    public void changeItem(int position, String text) {
        datas.set(position, text);
        notifyItemChanged(position);
    }

    public void addMoreItem(List<String> moreData) {
        final int size = datas.size();
        datas.addAll(moreData);
        notifyItemRangeChanged(size - 1, moreData.size());
    }

    public void changeLoadMoreStatus(int status) {
        load_status = status;
        notifyItemChanged(datas.size());
    }
}
