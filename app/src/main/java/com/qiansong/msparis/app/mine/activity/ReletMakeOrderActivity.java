package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.wardrobe.activity.PayActivity;

/**
 * 续租 -确认订单
 */
public class ReletMakeOrderActivity extends BaseActivity {
    ReletMakeOrderActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relet_make_order);
        context=this;
        setTitleBar();
        init();
        setListener();
    }
    //去支付
    public void to_pay(){
        startActivity(new Intent(context, PayActivity.class));
    }
    //监听
    private void setListener() {
        findViewById(R.id.to_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_pay();
            }
        });
    }

    //   填充
    private void init() {
    }


    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar)findViewById(R.id.title_bar);
        titleBar.setTitle("确认订单");//设置标题
        titleBar.setTitleColor(context.getResources().getColor(R.color.font19));
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
