package com.example.robin.coordinatorlayouttest.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (context != null) {
            context = null;
        }
    }
}
