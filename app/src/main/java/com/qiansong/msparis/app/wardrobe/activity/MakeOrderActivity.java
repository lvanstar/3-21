package com.qiansong.msparis.app.wardrobe.activity;

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
import com.qiansong.msparis.app.commom.bean.EditPakegeTimeBean;
import com.qiansong.msparis.app.commom.bean.OnePackagesBean;
import com.qiansong.msparis.app.commom.bean.OrderDailyBean;
import com.qiansong.msparis.app.commom.bean.PackagesBean;
import com.qiansong.msparis.app.commom.bean.PriceBean;
import com.qiansong.msparis.app.commom.bean.RentalMonitor;
import com.qiansong.msparis.app.commom.selfview.CalendarView.CalendarLayout;
import com.qiansong.msparis.app.commom.selfview.CalendarView.CalendarLayoutTwo;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.mine.activity.CouponActivity;
import com.qiansong.msparis.app.mine.bean.AddressBean;
import com.qiansong.msparis.app.wardrobe.adapter.MakeOrderAdapter;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.util.Eutil;
import com.qiansong.msparis.app.mine.activity.AddressActivity;
import com.qiansong.msparis.app.wardrobe.selfview.CalendarView;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 确认订单-日常
 */
public class MakeOrderActivity extends BaseActivity implements View.OnClickListener {
    public static String intent_key="MakeOrderActivity";
    public static int resultCode=66;//地址
    MakeOrderActivity context;//单一实力
    ListView sku_list;//商品
    ImageView yanqi;//选择
    LinearLayout yanqi_layout,youhui;//选择后触发
    TextView to_pay,time;
    EditText to_say;//备注
    TextView region_name,address_detail,contact_name,contact_mobile;//和接口同名看对应接口字段
    LinearLayout address,no_address;
    View activityRootView,bottom;
    boolean isSelect=false;//选择标示

    String all;
    OnePackagesBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        context=this;
        setTitleBar();
        all=  getIntent().getStringExtra(intent_key);
        if (null==all)return;
        bean=new Gson().fromJson(all,OnePackagesBean.class);
//        Eutil.makeLog("bean.getPackage_items().get(1).getId(): "+bean.getPackage_items().get(1).getId());
//        Eutil.makeLog("确认订单页商品数目00000000: "+bean.getPackage_items().size());
        for (int i=bean.getData().getPackage_items().size()-1;i>=0;i--){
            if (!bean.getData().getPackage_items().get(i).isSelect||null==bean.getData().getPackage_items().get(i).getId()||bean.getData().getPackage_items().get(i).isEmpty){
                bean.getData().getPackage_items().remove(i);
            }
        }
        Eutil.makeLog("package_id:"+bean.getData().getPackage_id()+"\nregion_code:"+bean.getData().getDelivery_region());
//        Eutil.makeLog("确认订单页商品数目: "+bean.getPackage_items().size());
        init();
        setlistener();
    }

    private void setlistener() {
        yanqi.setOnClickListener(this);
        to_pay.setOnClickListener(this);
        youhui.setOnClickListener(this);
        yanqi_layout.setOnClickListener(this);
        findViewById(R.id.set_address).setOnClickListener(this);
    }

    private void init() {
        activityRootView=findViewById(R.id.activityRootView);
        bottom=findViewById(R.id.bottom);

        address= (LinearLayout) findViewById(R.id.address);
        no_address= (LinearLayout) findViewById(R.id.no_address);
        region_name= (TextView) findViewById(R.id.region_name);
        address_detail= (TextView) findViewById(R.id.address_detail);
        contact_name= (TextView) findViewById(R.id.contact_name);
        contact_mobile= (TextView) findViewById(R.id.contact_mobile);

        time= (TextView) findViewById(R.id.time);
        to_say= (EditText) findViewById(R.id.to_say);
        youhui= (LinearLayout) findViewById(R.id.youhui);
        to_pay= (TextView) findViewById(R.id.to_pay);
        yanqi_layout= (LinearLayout) findViewById(R.id.yanqi_layout);
        yanqi_layout.setVisibility(View.GONE);
        yanqi= (ImageView) findViewById(R.id.yanqi);
        sku_list= (ListView) findViewById(R.id.sku_list);
        sku_list.setFocusable(false);
        sku_list.setAdapter(new MakeOrderAdapter(context,bean));
        ListUtils.setListViewHeightsOmag(sku_list);

        time.setText("租赁周期："+ Eutil.getStrTime2(bean.getData().getDelivery_date()+"")+"至"+Eutil.getStrTime2(bean.getData().getSend_back_date()+""));
        address();//地址
    }
    //刷新价格
    private void price(){
        Map<String,String> map=new Hashtable<>();
        map.put("access_token",MyApplication.token);
        map.put("package_id",bean.getData().getPackage_id());
        map.put("region_code",MyApplication.region_code);
        map.put("order_item","1");
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));

        HttpServiceClient.getInstance().price_daily(body).enqueue(new Callback<PriceBean>() {
            @Override
            public void onResponse(Call<PriceBean> call, Response<PriceBean> response) {
                if (!response.isSuccessful()||!"ok".equals(response.body().getStatus()))return;
                ((TextView)findViewById(R.id.more_time)).setText(response.body().getData().getMore_time());
                ((TextView)findViewById(R.id.deposit)).setText(response.body().getData().getDeposit());
                ((TextView)findViewById(R.id.coupon)).setText(response.body().getData().getCoupon());
                ((TextView)findViewById(R.id.member)).setText(response.body().getData().getMember());
                ((TextView)findViewById(R.id.express)).setText(response.body().getData().getExpress());
                ((TextView)findViewById(R.id.total)).setText(response.body().getData().getTotal());
            }

            @Override
            public void onFailure(Call<PriceBean> call, Throwable t) {

            }
        });

    }
    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar) findViewById(R.id.title_bar);
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.yanqi:yanqi();break;
            case R.id.to_pay:topay();break;
            case R.id.youhui:startActivity(new Intent(context,CouponActivity.class));break;
            case R.id.set_address:
                Intent intent=new Intent(context,AddressActivity.class);
                intent.putExtra("is_order","1");//1 确认订单  0 不是
                startActivityForResult(intent,resultCode);
                break;
            case R.id.yanqi_layout://延期
                to_yanqi();
                break;
        }
    }
    //    延期
    private void to_yanqi(){
        HttpServiceClient.getInstance().package_scheuule(MyApplication.token, "1", MyApplication.region_code).enqueue(new Callback<RentalMonitor>() {
            @Override
            public void onResponse(Call<RentalMonitor> call, Response<RentalMonitor> response) {

                if (response.isSuccessful()) {
                    if ("ok".equals(response.body().getStatus())) {
                        RentalMonitor.DataEntity entity = response.body().getData();
                        final CalendarView view = new CalendarView(context, entity,2);
                        view.show(activityRootView);

                        CalendarLayoutTwo.mConfirmV.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                view.dismiss();
                                //更新时间
                                setTime(bean.getData().getDelivery_date(),CalendarLayoutTwo.mCalendarPageTwo.getSelectedDate());
                            }
                        });
                    } else {
                        ContentUtil.makeToast(context, response.body().getError().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<RentalMonitor> call, Throwable t) {

            }
        });
    }
        //更新租赁周期
    private void setTime(final int start_time, final int end_time){
        Map<String,Object> map=new HashMap<>();
        map.put("access_token", MyApplication.token);
        map.put("package_id",bean.getData().getPackage_id());
        map.put("start_date",start_time);
        map.put("end_date",end_time);
        map.put("region_code",MyApplication.region_code);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));
        HttpServiceClient.getInstance().packages_time(body).enqueue(new Callback<EditPakegeTimeBean>() {
            @Override
            public void onResponse(Call<EditPakegeTimeBean> call, Response<EditPakegeTimeBean> response) {
                if (response.isSuccessful()&&"ok".equals(response.body().getStatus())){
                    ContentUtil.makeToast(context,"更新成功");
                    time.setText("租赁周期："+Eutil.getStrTime2(start_time+"")+"至"+Eutil.getStrTime2(end_time+""));

                }
            }

            @Override
            public void onFailure(Call<EditPakegeTimeBean> call, Throwable t) {

            }
        });
    }

    //提交订单
    private void topay(){
        if ("".equals(address_detail.getText().toString())){
            ContentUtil.makeToast(context,"请选择配送地址");
            return;
        }
        Map<String,String> map=new Hashtable<>();
        map.put("access_token",MyApplication.token);
        map.put("package_id",bean.getData().getPackage_id());
        map.put("user_address_id",addressBean.getId()+"");
        map.put("order_item","1");

        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));
        HttpServiceClient.getInstance().order_daily(body).enqueue(new Callback<OrderDailyBean>() {
            @Override
            public void onResponse(Call<OrderDailyBean> call, Response<OrderDailyBean> response) {
                if (response.isSuccessful()&&"ok".equals(response.body().getStatus())){
                    Eutil.makeLog("创建订单成功");
                    Intent intent=new Intent(context,PayActivity.class);
                    intent.putExtra("order_id",response.body().getData().getOrder_id());
                    intent.putExtra("order_no",response.body().getData().getOrder_no());
                    intent.putExtra("pay_amount",response.body().getData().getPay_amount());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<OrderDailyBean> call, Throwable t) {

            }
        });

    }
    AddressBean.DataBean.RowsBean addressBean ;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==MakeOrderActivity.resultCode){//地址
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
     * 延期
     */
    private void yanqi() {
        if (isSelect){
            yanqi.setImageResource(R.mipmap.select_0_long);
            yanqi_layout.setVisibility(View.GONE);
            isSelect=false;
        }else {
            yanqi.setImageResource(R.mipmap.select_1_long);
            yanqi_layout.setVisibility(View.VISIBLE);
            isSelect=true;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

    }



    /**
     * 地址的逻辑
     */
    private void address(){

        HttpServiceClient.getInstance().address(MyApplication.token,bean.getData().getDelivery_region(),null,null).enqueue(new Callback<AddressBean>() {
            @Override
            public void onResponse(Call<AddressBean> call, Response<AddressBean> response) {
                if (!response.isSuccessful() || !"ok".equals(response.body().getStatus())){
                    if (response.body().getData().getRows().size()==0){
                        no_address.setVisibility(View.VISIBLE);
                        address.setVisibility(View.GONE);
                    }
                    return;
                }
                new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        ((ScrollView)findViewById(R.id.scrollView)).scrollTo(0,0);
                        return false;
                    }
                }).sendEmptyMessageDelayed(0,100) ;
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
}
