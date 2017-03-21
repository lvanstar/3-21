package com.qiansong.msparis.app.wardrobe.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.AliBean;
import com.qiansong.msparis.app.commom.bean.WeixinPreBean;
import com.qiansong.msparis.app.commom.bean.YajinOrderBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.PayHelp;
import com.qiansong.msparis.app.homepage.util.Eutil;
import com.qiansong.msparis.app.wardrobe.util.WXpayUtils;

import java.util.Hashtable;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 支付订单
 */
public class PayActivity extends BaseActivity implements View.OnClickListener {
    PayActivity context;
    ImageView select_weixin, select_zhifubao;

    private int payway = 1;//0微信 1支付宝
    private String order_no;//订单号
    private String order_id;//订单id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        context = this;
        order_no = getIntent().getStringExtra("order_no");//订单号
        order_id = getIntent().getStringExtra("order_id");//订单id
        if (order_no == null) order_no = "";
        if (order_id == null) order_id = "";
        setTitleBar();
        init();
        setListener();
    }

    //监听
    private void setListener() {
        findViewById(R.id.topay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_pay(view);
            }
        });
    }

    //填充
    private void init() {
        select_weixin = (ImageView) findViewById(R.id.select_weixin);
        select_zhifubao = (ImageView) findViewById(R.id.select_zhifubao);
        Eutil.makeLog("pay_amount: " + getIntent().getStringExtra("pay_amount"));
        String money = getIntent().getStringExtra("pay_amount");
        if (money == null) return;
        if (money.length() > 2) {
            String s1 = money.substring(0, money.length() - 2);
            String s2 = money.substring(money.length() - 2);
            ((TextView) findViewById(R.id.pay_amount)).setText("￥" + s1 + "." + s2);
        } else if (money.length() == 2) {
            ((TextView) findViewById(R.id.pay_amount)).setText("￥" + "0." + money);
        } else if (money.length() < 2) {
            ((TextView) findViewById(R.id.pay_amount)).setText("￥" + "0.0" + money);
        }
    }

    //微信
    public void weixin(View view) {
        payway = 0;
        select_weixin.setImageResource(R.mipmap.select_1);
        select_zhifubao.setImageResource(R.mipmap.select_0);
    }

    //支付宝
    public void zhifubao(View view) {
        payway = 1;
        select_weixin.setImageResource(R.mipmap.select_0);
        select_zhifubao.setImageResource(R.mipmap.select_1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }

    }

    //    去支付
    public void to_pay(View view) {
        if (payway == 0) {
            //微信
            PayHelp.getInstance().weixin_pay(context,"3",order_id,new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    if (1==msg.what){//支付成功
                        //...
                    }else if (0==msg.what){//支付失败
                        //...
                    }
                    return false;
                }
            }));
        } else if (payway == 1) {
            //支付宝
            PayHelp.getInstance().alipay(context,"3",order_id,new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    if (1==msg.what){//支付成功
                     //...
                    }else if (0==msg.what){//支付失败
                     //...
                    }
                    return false;
                }
            }));

        }
//        startActivity(new Intent(context,PaymentResultsActivity.class));
    }


    //设置标题栏
    private void setTitleBar() {
        ETitleBar titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("支付订单");//设置标题
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
