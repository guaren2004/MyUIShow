package com.example.robin.coordinatorlayouttest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.robin.coordinatorlayouttest.R;

/**
 * 展示原图的 Activity
 */
public class ShowActivity extends BaseActivity {

    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        final Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        initView();
    }

    private void initView() {
        final ImageView img_show = (ImageView) findViewById(R.id.img_show);
        /**
         * 这里显示大图, 必须把diskCacheStrategy的策略写出来,才能从硬盘缓存中读取到原图
         */
        Glide.with(this)
                .load(mUrl)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(img_show);
    }
}
