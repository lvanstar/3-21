package com.qiansong.msparis.app.wardrobe.activity;

import android.os.Bundle;
import android.view.View;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;

/**
 * Created by lizhaozhao on 2017/2/17.
 *
 * 尺码说明
 */

public class SizeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_size);


        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
