package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;

/**
 * 发票 - yechen
 */
public class InvoiceActivity extends AppCompatActivity {
    InvoiceActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        context=this;
        setTitleBar();
        init();
    }

    private void init() {
        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开票
                startActivity(new Intent(context,MakeInvoiceActivity.class));
            }
        });
    }

    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar)findViewById(R.id.title_bar);
        titleBar.setTitle("发票");//设置标题
        titleBar.setTitleColor(this.getResources().getColor(R.color.font19));
        titleBar.setLeftImageResource(R.mipmap.back);//设置左边图标
        titleBar.setRightTitle("开票记录");
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
                startActivity(new Intent(context,InvoiceRecordsActivity.class));
            }
        });
    }
}
