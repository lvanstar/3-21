package com.qiansong.msparis.app.wardrobe.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;

/**
 * 支付结果页 仅用于日常和礼服租赁订单
 */
public class PaymentResultsActivity extends AppCompatActivity {
    PaymentResultsActivity context;
    public String isSuccess;//1成功 0失败
    ImageView pay_img;
    TextView pay_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_results);
        isSuccess=getIntent().getStringExtra("isSuccess");
        context=this;
        setTitleBar();
        pay_img= (ImageView) findViewById(R.id.pay_img);
        pay_text= (TextView) findViewById(R.id.pay_text);
        if ("0".equals(isSuccess)){
            pay_img.setImageResource(R.mipmap.pay_fail);
            pay_text.setText("支付失败");
        }else if ("1".equals(isSuccess)){
            pay_img.setImageResource(R.mipmap.pay_sucess);
            pay_text.setText("支付成功");
        }
    }

    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("支付结果");//设置标题
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
