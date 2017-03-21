package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;

/**
 * 还衣结果 ye
 */
public class ReturnResultActivity extends BaseActivity {
    ReturnResultActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_result);
        context=this;
        setTitleBar();
        init();
        setListener();
    }
    //填充
    private void init() {
    }
    //监听
    private void setListener() {
        findViewById(R.id.to_evaluate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_evaluate();
            }
        });
    }

    //去评价
   public void to_evaluate(){
      startActivity(new Intent(context,EvaluateActivity.class));
   }
    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("还衣结果");//设置标题
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
                startActivity(new Intent(context,ClothingRecordActivity.class));
            }
        });
    }
}
