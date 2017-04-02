package com.example.robin.coordinatorlayouttest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.activity.ShowActivity;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Glide 的使用
 */
public class WaterfallRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] imageUrls;
    private Context context;
    private Fragment fragment;

    public WaterfallRVAdapter(String[] imageUrls, Context context, Fragment fragment) {
        this.imageUrls = imageUrls;
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.cardview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        System.out.println("WaterfallRVAdapter - " + "onBindViewHolder");
        final ViewHolder mHolder = (ViewHolder) holder;
        mHolder.view.setTag("ItemView");
        // 如果下面这个 setTag 方法执行了, 后面 Glide 会报错: You must not call setTag() on a view Glide is targeting
        // 解决办法: 不设置 setTag 或者 使用 setTag(int key, Object tag)
//        mHolder.img_item.setTag("ImageView");
        final String url = imageUrls[position];

//        final RequestManager requestManager = Glide.with(fragment);
//        final DrawableTypeRequest<String> request = requestManager.load(url);
//        final DrawableRequestBuilder<String> builder = request.error(R.mipmap.ic_launcher);
//        builder.into(mHolder.img_item);

        Glide.with(fragment)
                .load(url)
                .skipMemoryCache(true) // 跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL) // 即缓存了 imageView 适配的图片,又缓存了原图
                .dontAnimate() // 取消动画
                .placeholder(R.mipmap.ic_launcher) // 占位图片
//                .error(R.mipmap.ic_launcher)
//                .thumbnail(0.1f) // 请求给定系数(0到1之间)的缩略图
                .override(300, 300) // 指定加载资源的宽和高的像素
                .bitmapTransform(new CropCircleTransformation(context)) // 圆形图
                .into(mHolder.img_item);

        mHolder.img_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(fragment.getActivity(), ShowActivity.class);
                intent.putExtra("url", url);
                fragment.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageUrls.length;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private ImageView img_item;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            img_item = (ImageView) itemView.findViewById(R.id.img_item);
        }
    }
}
