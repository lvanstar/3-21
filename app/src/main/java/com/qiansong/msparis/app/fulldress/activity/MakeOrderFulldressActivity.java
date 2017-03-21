package com.qiansong.msparis.app.fulldress.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.ShoppingCartBean;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.mine.activity.CouponActivity;
import com.qiansong.msparis.app.mine.bean.AddressBean;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.fulldress.adapter.MakeOrderFulldressAdapter;
import com.qiansong.msparis.app.homepage.util.Eutil;
import com.qiansong.msparis.app.mine.activity.AddressActivity;
import com.qiansong.msparis.app.wardrobe.activity.MakeOrderActivity;
import com.qiansong.msparis.app.wardrobe.activity.PayActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 确认订单-礼服
 */
public class MakeOrderFulldressActivity extends BaseActivity implements View.OnClickListener {
    public static String intent_key = "MakeOrderFulldressActivity";
    public static int resultCode = 66;
    MakeOrderFulldressActivity context;//单一实力
    ListView sku_list;//商品
    ImageView yanqi;//选择
    LinearLayout yanqi_layout, youhui;//选择后 触发
    TextView to_pay, time;
    EditText to_say;//备注
    TextView region_name, address_detail, contact_name, contact_mobile;//和接口同名看对应接口字段
    LinearLayout address, no_address;
    boolean isSelect = false;//选择标示

    String all;
    ShoppingCartBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        context = this;
        setTitleBar();
        all = getIntent().getStringExtra(intent_key);
        if (null == all) return;
        bean = new Gson().fromJson(all, ShoppingCartBean.class);
//        Eutil.makeLog("SIZE-0:");
        init();
        setlistener();
    }

    private void setlistener() {
        yanqi.setOnClickListener(this);
        to_pay.setOnClickListener(this);
        youhui.setOnClickListener(this);
        findViewById(R.id.set_address).setOnClickListener(this);
    }

    private void init() {
        for (int s = bean.getData().size() - 1; s >= 0; s--) {
            for (int i = bean.getData().get(s).getItems().size() - 1; i >= 0; i--) {
                if (!bean.getData().get(s).getItems().get(i).isSelect) {
                    bean.getData().get(s).getItems().remove(i);
                }
            }
        }
        for (int s = bean.getData().size() - 1; s >= 0; s--) {
            if (bean.getData().get(s).getItems().size()==0) {
                bean.getData().remove(s);
            }
        }
        address = (LinearLayout) findViewById(R.id.address);
        no_address = (LinearLayout) findViewById(R.id.no_address);
        region_name = (TextView) findViewById(R.id.region_name);
        address_detail = (TextView) findViewById(R.id.address_detail);
        contact_name = (TextView) findViewById(R.id.contact_name);
        contact_mobile = (TextView) findViewById(R.id.contact_mobile);

        time = (TextView) findViewById(R.id.time);
        to_say = (EditText) findViewById(R.id.to_say);
        youhui = (LinearLayout) findViewById(R.id.youhui);
        to_pay = (TextView) findViewById(R.id.to_pay);
        yanqi_layout = (LinearLayout) findViewById(R.id.yanqi_layout);
        yanqi_layout.setVisibility(View.GONE);
        yanqi = (ImageView) findViewById(R.id.yanqi);
        sku_list = (ListView) findViewById(R.id.sku_list);
        sku_list.setFocusable(false);
        sku_list.setAdapter(new MakeOrderFulldressAdapter(context, bean));
        ListUtils.setListViewHeightsOmag(sku_list);

        time.setText("租赁周期：" + Eutil.getStrTime2(bean.getData().get(0).getStarttime() + "") + "至" + Eutil.getStrTime2(bean.getData().get(0).getEndtime() + ""));
        address();//地址
    }

    //设置标题栏
    private void setTitleBar() {
        ETitleBar titleBar = (ETitleBar) findViewById(R.id.title_bar);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== MakeOrderActivity.resultCode){//地址
            AddressBean.DataBean.RowsBean addressBean=new Gson().fromJson(data.getStringExtra(MakeOrderActivity.intent_key),AddressBean.DataBean.RowsBean.class);
            no_address.setVisibility(View.GONE);
            address.setVisibility(View.VISIBLE);
            region_name.setText(addressBean.getRegion_name());
            address_detail.setText(addressBean.getAddress_detail());
            contact_name.setText(addressBean.getContact_name());
            contact_mobile.setText(addressBean.getContact_mobile());
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.yanqi:
                yanqi();
                break;
            case R.id.to_pay:
                startActivity(new Intent(context, PayActivity.class));
                break;
            case R.id.youhui:
                startActivity(new Intent(context, CouponActivity.class));
                break;
            case R.id.set_address:
                Intent intent=new Intent(context,AddressActivity.class);
                intent.putExtra("is_order","1");//1 确认订单  0 不是
                startActivityForResult(intent,resultCode);
                break;
        }
    }

    /**
     * 延期
     */
    private void yanqi() {
        if (isSelect) {
            yanqi.setImageResource(R.mipmap.select_0_long);
            yanqi_layout.setVisibility(View.GONE);
            isSelect = false;
        } else {
            yanqi.setImageResource(R.mipmap.select_1_long);
            yanqi_layout.setVisibility(View.VISIBLE);
            isSelect = true;
        }
    }

    /**
     * 地址的逻辑
     */
    private void address() {

        HttpServiceClient.getInstance().address(MyApplication.token, null, null, null).enqueue(new Callback<AddressBean>() {
            @Override
            public void onResponse(Call<AddressBean> call, Response<AddressBean> response) {
                if (!response.isSuccessful() || !"ok".equals(response.body().getStatus())) {
                    if (response.body().getData().getRows().size() == 0) {
                        no_address.setVisibility(View.VISIBLE);
                        address.setVisibility(View.GONE);
                    }
                    return;
                }
                new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        ((ScrollView) findViewById(R.id.scrollView)).scrollTo(0, 0);
                        return false;
                    }
                }).sendEmptyMessageDelayed(0, 100);
                if (null==response.body().getData().getRows()||response.body().getData().getRows().size()==0){
                    no_address.setVisibility(View.VISIBLE);
                    address.setVisibility(View.GONE);
                    return;
                }
                no_address.setVisibility(View.GONE);
                address.setVisibility(View.VISIBLE);

                region_name.setText(response.body().getData().getRows().get(0).getRegion_name());
                address_detail.setText(response.body().getData().getRows().get(0).getAddress_detail());
                contact_name.setText(response.body().getData().getRows().get(0).getContact_name());
                contact_mobile.setText(response.body().getData().getRows().get(0).getContact_mobile());
            }

            @Override
            public void onFailure(Call<AddressBean> call, Throwable t) {

            }
        });
    }
}
