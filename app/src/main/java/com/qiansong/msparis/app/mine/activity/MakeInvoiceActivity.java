package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.mine.bean.AddressBean;
import com.qiansong.msparis.app.wardrobe.activity.MakeOrderActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 我要开票
 */
public class MakeInvoiceActivity extends BaseActivity {
    MakeInvoiceActivity context;
    public static int resultCode=66;//地址
    TextView region_name,address_detail,contact_name,contact_mobile;//和接口同名看对应接口字段
    LinearLayout address,no_address;
    int taitou=0;//0 个人  1公司
    ImageView gongsi,geren;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_invoice);
        context=this;
        setTitleBar();
        init();
    }

    private void init() {
        gongsi= (ImageView) findViewById(R.id.gongsi);
        geren= (ImageView) findViewById(R.id.geren);

        address= (LinearLayout) findViewById(R.id.address);
        no_address= (LinearLayout) findViewById(R.id.no_address);
        region_name= (TextView) findViewById(R.id.region_name);
        address_detail= (TextView) findViewById(R.id.address_detail);
        contact_name= (TextView) findViewById(R.id.contact_name);
        contact_mobile= (TextView) findViewById(R.id.contact_mobile);
        address();
    }
    //地址点击
    public void dizhi(View view){
        Intent intent=new Intent(context,AddressActivity.class);
        intent.putExtra("is_order","1");//1 确认订单  0 不是
        startActivityForResult(intent,resultCode);
    }
    AddressBean.DataBean.RowsBean addressBean ;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== MakeOrderActivity.resultCode){//地址
            addressBean=new Gson().fromJson(data.getStringExtra(MakeOrderActivity.intent_key),AddressBean.DataBean.RowsBean.class);
            no_address.setVisibility(View.GONE);
            address.setVisibility(View.VISIBLE);
            region_name.setText(addressBean.getRegion_name());
            address_detail.setText(addressBean.getAddress_detail());
            contact_name.setText(addressBean.getContact_name());
            contact_mobile.setText(addressBean.getContact_mobile());
        }
    }
    /**
     * 地址的逻辑
     */
    private void address(){

        HttpServiceClient.getInstance().address(MyApplication.token,null,null,null).enqueue(new Callback<AddressBean>() {
            @Override
            public void onResponse(Call<AddressBean> call, Response<AddressBean> response) {
                if (!response.isSuccessful() || !"ok".equals(response.body().getStatus())){
                    if (response.body().getData().getRows().size()==0){
                        no_address.setVisibility(View.VISIBLE);
                        address.setVisibility(View.GONE);
                    }
                    return;
                }
                if (null==response.body().getData().getRows()||response.body().getData().getRows().size()==0){
                    no_address.setVisibility(View.VISIBLE);
                    address.setVisibility(View.GONE);
                    return;
                }
                no_address.setVisibility(View.GONE);
                address.setVisibility(View.VISIBLE);

                addressBean=response.body().getData().getRows().get(0);

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
    //公司
    public void gongsi(View view){
        taitou=1;
        gongsi.setImageResource(R.mipmap.gongsi_1);
        geren.setImageResource(R.mipmap.geren_0);
    }
    //个人
    public void geren(View view){
        taitou=0;
        gongsi.setImageResource(R.mipmap.gongsi_0);
        geren.setImageResource(R.mipmap.geren_1);
    }
    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar)findViewById(R.id.title_bar);
        titleBar.setTitle("发票");//设置标题
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
