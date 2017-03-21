package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;

/**
 * 扫码改运单号
 */
public class EditWaybillNumberActivity extends EScanActiviy {
    //设置布局
    @Override
    public void init_layout() {
        setContentView(R.layout.activity_editwaybillnumber);
    }

    //扫面前设置title 等
    @Override
    public void init() {
        setTitleBar();
        findViewById(R.id.to_write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditWaybillNumberActivity.this,ManualInputActivity.class));
                finish();
            }
        });
    }
    //扫描后拿到结果字符串
    @Override
    public void todo(String cotent) {
        ContentUtil.makeToast(this,"运单号："+cotent);
        finish();
    }







    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar)findViewById(R.id.title_bar);
        titleBar.setTitle("更改运单");//设置标题
        titleBar.setTitleColor(this.getResources().getColor(R.color.font19));
        titleBar.setLeftImageResource(R.mipmap.back);//设置左边图标
//        titleBar.setRightImageResource(R.mipmap.ic_launcher);//设置右边图标
        titleBar.setBackgroundColor(Color.parseColor("#ffffff"));//设置背景
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
            @Override
            public void onClick(View view) {
            }
        });
    }
}
