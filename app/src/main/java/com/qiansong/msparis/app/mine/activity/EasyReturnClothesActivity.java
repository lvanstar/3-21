package com.qiansong.msparis.app.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.activity.MainActivity;
import com.qiansong.msparis.app.commom.bean.ReturnBean;
import com.qiansong.msparis.app.commom.bean.UserWardrobeBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.util.Eutil;
import com.qiansong.msparis.app.mine.adapter.BookingAdapter;
import com.qiansong.msparis.app.mine.bean.AddressBean;
import com.qiansong.msparis.app.mine.view.ArrayData;
import com.qiansong.msparis.app.mine.view.OptionsWindowHelper;
import com.qiansong.msparis.app.wardrobe.activity.MakeOrderActivity;

import java.util.ArrayList;
import java.util.List;

import cn.jeesoft.widget.pickerview.CharacterPickerView;
import cn.jeesoft.widget.pickerview.CharacterPickerWindow;
import cn.jeesoft.widget.pickerview.OnOptionChangedListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 一键还衣-叶晨
 */
public class EasyReturnClothesActivity extends BaseActivity {

    EasyReturnClothesActivity context;
    private RecyclerView recyclerview_horizontal;

//    public static int resultCode=66;//地址
    TextView region_name,address_detail,contact_name,contact_mobile;//和接口同名看对应接口字段
    LinearLayout address,no_address;

    ReturnBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_return_clothes);
        context=this;
        setTitleBar();
        init();
        Listener();
    }

    private void Listener() {
        findViewById(R.id.to_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result();
            }
        });
        findViewById(R.id.address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,AddressActivity.class));
            }
        });
        findViewById(R.id.no_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,AddressActivity.class);
                intent.putExtra("is_order","1");//1 确认订单  0 不是
                startActivityForResult(intent,resultCode);
            }
        });
        findViewById(R.id.address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,AddressActivity.class);
                intent.putExtra("is_order","1");//1 确认订单  0 不是
                startActivityForResult(intent,resultCode);
            }
        });
    }
    public static int resultCode=66;//地址
    private void init() {

        address= (LinearLayout) findViewById(R.id.address);
        no_address= (LinearLayout) findViewById(R.id.no_address);
        region_name= (TextView) findViewById(R.id.region_name);
        address_detail= (TextView) findViewById(R.id.address_detail);
        contact_name= (TextView) findViewById(R.id.contact_name);
        contact_mobile= (TextView) findViewById(R.id.contact_mobile);


        mengban=findViewById(R.id.mengban);
        recyclerview_horizontal = (RecyclerView) findViewById(R.id.recyclerview_horizontal);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview_horizontal.setLayoutManager(linearLayoutManager);



        recyclerview_horizontal.setFocusable(false);


        HttpServiceClient.getInstance().order_return_confirm(MyApplication.token,"1").enqueue(new Callback<ReturnBean>() {
            @Override
            public void onResponse(Call<ReturnBean> call, Response<ReturnBean> response) {
                if (response.isSuccessful()&&"ok".equals(response.body().getStatus())){
                    bean=response.body();
                   if (null==bean.getData().getShipping_address()||"".equals(bean.getData().getShipping_address())){
                       address();
                   }else {
                       no_address.setVisibility(View.GONE);
                       address.setVisibility(View.VISIBLE);
                       region_name.setText(bean.getData().getShipping_region_name());
                       address_detail.setText(bean.getData().getShipping_address());
                       contact_name.setText(bean.getData().getShipping_name());
                       contact_mobile.setText(bean.getData().getShipping_mobile());
                   }
                    //设置时间
                    showPickerview();
                    //设置适配器
                    GalleryAdapter mAdapter = new GalleryAdapter(context);
                    recyclerview_horizontal.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<ReturnBean> call, Throwable t) {
                Eutil.makeLog(t.getMessage().toString());
            }
        });
    }

    AddressBean.DataBean.RowsBean addressBean ;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== 66){//地址
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
    /**
     *选择器
     */
     CharacterPickerWindow window;
    View mengban;
    private void showPickerview(){
        ArrayData.init(bean.getData().getPickup_date(),bean.getData().getPickup_time());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        CharacterPickerView pickerView = new CharacterPickerView(this);
//        activity_easy_return_clothes.addView(pickerView, layoutParams);

        //初始化选项数据
        OptionsWindowHelper.setPickerData(pickerView);

        //设置监听事件
        pickerView.setOnOptionChangedListener(new OnOptionChangedListener() {
            @Override
            public void onOptionChanged(int option1, int option2, int option3) {

                if (mengban.getVisibility()!=View.GONE)
                    mengban.setVisibility(View.GONE);
                Log.e("test", option1 + "," + option2 + "," + option3);
            }
        });
        //初始化
        window = OptionsWindowHelper.builder(context, new OptionsWindowHelper.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(String province, String city, String area) {
                if (mengban.getVisibility()!=View.GONE)
                    mengban.setVisibility(View.GONE);
                //如果都为空 那点击的是取消按钮 直接不改变界面吧
                if ("".equals(province)&&"".equals(city)&&"".equals(area))return;

                Log.e("main", province + "," + city + "," + area);
            }
        });


    }
    //显示选择
    public void showSelect(View view){
        if (mengban.getVisibility()!=View.VISIBLE){
            mengban.setVisibility(View.VISIBLE);
        }
        if (window!=null)
        window.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }
    //隐藏蒙版
    public void hide(View view){
        if (mengban.getVisibility()!=View.GONE)
            mengban.setVisibility(View.GONE);
    }
    //还衣结果
    public void result(){
       startActivity(new Intent(context,ReturnResultActivity.class));
    }

    public class GalleryAdapter extends
            RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
        private LayoutInflater mInflater;

        public GalleryAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View arg0) {
                super(arg0);
            }

            SimpleDraweeView image_url;
            TextView specification,product_brand;
            ImageView status, type_id;
        }

        @Override
        public int getItemCount() {
            return bean == null ? 0 : bean.getData().getProduct().size();
        }

        /**
         * 创建ViewHolder
         */
        @Override
        public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.item_booking_sku,
                    viewGroup, false);
            GalleryAdapter.ViewHolder viewHolder = new GalleryAdapter.ViewHolder(view);
            viewHolder.image_url = (SimpleDraweeView) view.findViewById(R.id.image_url);
            viewHolder.specification = (TextView) view.findViewById(R.id.specification);
            viewHolder.status = (ImageView) view.findViewById(R.id.status);
            viewHolder.type_id = (ImageView) view.findViewById(R.id.type_id);
            viewHolder.product_brand= (TextView) view.findViewById(R.id.product_brand);
            return viewHolder;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final GalleryAdapter.ViewHolder viewHolder, final int i) {
            ExclusiveUtils.setImageUrl(viewHolder.image_url, bean.getData().getProduct().get(i).getImage_url(), -1);
            viewHolder.specification.setText(bean.getData().getProduct().get(i).getSpecification());
            viewHolder.product_brand.setText(bean.getData().getProduct().get(i).getProduct_brand());
            if (2 == bean.getData().getProduct().get(i).getType_id()) {
                viewHolder.type_id.setVisibility(View.VISIBLE);
            } else {
                viewHolder.type_id.setVisibility(View.GONE);
            }
            switch (bean.getData().getProduct().get(i).getStatus()) {
                case 1:
                    viewHolder.status.setVisibility(View.VISIBLE);
                    viewHolder.status.setImageResource(R.mipmap.weifahuo);
                    break;
                case 5:
                    viewHolder.status.setVisibility(View.VISIBLE);
                    viewHolder.status.setImageResource(R.mipmap.yituihuo);
                    break;
                case 8:
                    viewHolder.status.setVisibility(View.VISIBLE);
                    viewHolder.status.setImageResource(R.mipmap.yiguihuan);
                    break;
                default:
                    viewHolder.status.setVisibility(View.GONE);
                    break;
            }
        }
    }
    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("一键还衣");//设置标题
        titleBar.setTitleColor(context.getResources().getColor(R.color.font19));
        titleBar.setLeftImageResource(R.mipmap.back);//设置左边图标
//        titleBar.setRightImageResource(R.mipmap.mine_edit_yundan);//设置右边图标
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
//                startActivity(new Intent(context,ClothingRecordActivity.class));
            }
        });
    }


}
