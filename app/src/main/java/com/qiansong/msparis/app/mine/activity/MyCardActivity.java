package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.adapter.GuideAdapter;
import com.qiansong.msparis.app.commom.bean.BannerBean;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.selfview.TXCirclePageIndicator;
import com.qiansong.msparis.app.commom.selfview.showimage.ImageDetailFragment;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.adapter.MyCardAdapter;
import com.qiansong.msparis.app.mine.adapter.MyCardItemAdapter;
import com.qiansong.msparis.app.mine.bean.MyCardBean;
import com.qiansong.msparis.app.mine.util.HackyViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * kevin.cao
 * <p>
 * 我的女神卡
 */
public class MyCardActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.my_card_button_layout)
    LinearLayout myCardButtonLayout;
    @InjectView(R.id.my_card_jihuo)
    TextView myCardJihuo;
    @InjectView(R.id.my_card_shenji)
    TextView myCardShenji;
    @InjectView(R.id.my_card_xufei)
    TextView myCardXufei;
    @InjectView(R.id.my_card_leixing)
    TextView myCardLeixing;
    @InjectView(R.id.my_card_time)
    TextView myCardTime;
    @InjectView(R.id.my_card_listView)
    GridView myCardListView;
    @InjectView(R.id.my_card_mingxi)
    LinearLayout myCardMingxi;
    @InjectView(R.id.my_card_shiyon)
    TextView myCardShiyon;


    /**
     * 滑动模块
     */
    @InjectView(R.id.card_pager)
    ViewPager cardPager;
    @InjectView(R.id.card_indicator)
    TXCirclePageIndicator cardIndicator;
    /**
     * 滑动模块 适配器
     */
    private MyCardAdapter guideBannerAdapter;
    private ETitleBar titleBar;

    //第几张卡
    private int pagerPosition = 0;

    //用户tonken
    private String token = "";
    private MyCardBean bean = null;
    private BaseBean baseBean;
    private Bundle savedInstanceState;
    private MyCardItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
    }

    /**
     * 加载页面
     */
    public void initView() {
        cardIndicator.setOnPageChangeListener(this);
        cardIndicator.setFillColor(getResources().getColor(R.color.color_f964b3));


        //设置自适应高度
        ListUtils.setGridViewHeightBasedOnChildren(myCardListView, 4);

        adapter = new MyCardItemAdapter(this);
        myCardListView.setAdapter(adapter);
    }

    /**
     * 网络请求  获取页面基本数据
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        if (token != null && token.length() > 0) {
            HttpServiceClient.getInstance().user_card(token)
                    .enqueue(new Callback<MyCardBean>() {
                        @Override
                        public void onResponse(Call<MyCardBean> call, Response<MyCardBean> response) {
                            dialog.cancel();
                            if (response.isSuccessful()) {
                                bean = response.body();
                                if ("ok".equals(bean.getStatus())) {
                                    initData();
                                } else {
                                    ToastUtil.showMessage(bean.getError().getMessage());
                                }
                            } else {
                            }
                        }

                        @Override
                        public void onFailure(Call<MyCardBean> call, Throwable t) {

                        }
                    });
        }

    }

    /**
     * 暂停会员期
     */
    public void pauseaCrd(String id) {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        Map<String,Object> map=new HashMap<>();
        map.put("access_token",token);
        map.put("id",id);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));

        HttpServiceClient.getInstance().pause_card(body)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            baseBean = response.body();
                            if ("ok".equals(baseBean.getStatus())) {
                                ToastUtil.showMessage("暂停成功");
                            } else {
                                ToastUtil.showMessage(baseBean.getError().getMessage());
                            }
                        } else {
                        }
                    }
                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {

                    }
                });
    }

    /**
     * 激活我的女神卡
     */
    public void activationCard(String id) {
        if (token != null && token.length() > 0) {
            dialog.show();
            Map<String, Object> map = new HashMap<>();
            map.put("access_token", token);
            map.put("id", id);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));

            HttpServiceClient.getInstance().activation(body)
                    .enqueue(new Callback<BaseBean>() {
                        @Override
                        public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                            dialog.cancel();
                            if (response.isSuccessful()) {
                                baseBean = response.body();
                                if (baseBean != null) {
                                    if ("ok".equals(baseBean.getStatus())) {
                                        ToastUtil.showMessage("激活成功");
                                        requestData();
                                    } else {
                                        ToastUtil.showMessage(baseBean.getError().getMessage());
                                    }
                                }
                            } else {
                                ToastUtil.showMessage("网络异常");
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseBean> call, Throwable t) {

                        }
                    });
        }
    }

    /**
     * 加载上部分
     */
    public void initData() {

        guideBannerAdapter = new MyCardAdapter(this);
//        bean.getData().getImages().add("https://gdp.alicdn.com/imgextra/i2/890482188/TB2dlBUgmFjpuFjSszhXXaBuVXa_!!890482188.jpg");
//        bean.getData().getImages().add("https://gdp.alicdn.com/imgextra/i3/446338500/TB2CZsqg9JjpuFjy0FdXXXmoFXa_!!446338500.jpg");
        guideBannerAdapter.setBannerList(bean.getData().getImages());
        guideBannerAdapter.notifyDataSetChanged();

        cardPager.setAdapter(guideBannerAdapter);
        cardIndicator.setViewPager(cardPager);

        setData();
    }

    /**
     * 设置值
     */
    public void setData() {
        //判断是否激活0.未激活 1.激活
        int position = pagerPosition;
        if (Integer.parseInt(bean.getData().getRows().get(position).getIs_activate()) == 0) {
            myCardButtonLayout.setVisibility(View.GONE);
            myCardJihuo.setVisibility(View.VISIBLE);
        } else if (Integer.parseInt(bean.getData().getRows().get(position).getIs_activate()) == 1) {
            myCardButtonLayout.setVisibility(View.VISIBLE);
            myCardJihuo.setVisibility(View.GONE);
        }


        myCardLeixing.setText(bean.getData().getRows().get(position).getName());
        myCardTime.setText(bean.getData().getRows().get(position).getExpiry_date());
        adapter.setData(bean.getData().getRows().get(position).getCard_role());
        myCardShiyon.setText(bean.getData().getRows().get(position).getNotice());


    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.my_card_jihuo, R.id.my_card_shenji, R.id.my_card_xufei, R.id.my_card_mingxi, R.id.card_stop})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            //激活此卡
            case R.id.my_card_jihuo:
                activationCard(bean.getData().getRows().get(pagerPosition).getId());
                break;
            //升级
            case R.id.my_card_shenji:
                break;
            //续费
            case R.id.my_card_xufei:
                intent.setClass(MyCardActivity.this, RenewCardActivity.class);
//                intent.putExtra("card_id", bean.getData().getRows().get(pagerPosition).getId());
                startActivity(intent);
                break;
            //明细
            case R.id.my_card_mingxi:
                intent.setClass(MyCardActivity.this, UseDetailsActivity.class);
                intent.putExtra("card_id", bean.getData().getRows().get(pagerPosition).getId());
                startActivity(intent);
                break;
            //暂停卡
            case R.id.card_stop:
//                pauseaCrd(bean.getData().getRows().get(pagerPosition).getId());
                intent.setClass(MyCardActivity.this, RenewCardActivity.class);
//                intent.putExtra("card_id", bean.getData().getRows().get(pagerPosition).getId());
                startActivity(intent);
                break;
        }
    }


    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("我的女神卡");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                MyCardActivity.super.onBackPressed();
            }
        });
//        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(AppointmentTimeActivity.this, AppointmentRecordActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    /*******/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ToastUtil.showMessage("position---" + position);
//        pagerPosition = position;
//        setData();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
