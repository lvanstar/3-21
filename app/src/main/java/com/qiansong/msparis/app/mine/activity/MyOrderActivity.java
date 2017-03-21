package com.qiansong.msparis.app.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.mine.fragment.MyLeaseOrderFragment;
import com.qiansong.msparis.app.commom.adapter.MyMainAdapter;
import com.qiansong.msparis.app.commom.util.BarTintManger;
import com.qiansong.msparis.app.mine.fragment.BuyOrderFragment;
import com.qiansong.msparis.app.mine.fragment.VipOrderFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/2/21.
 *
 * 我的订单
 */

public class MyOrderActivity extends FragmentActivity {

    @InjectView(R.id.back_btn)
    LinearLayout backBtn;
    @InjectView(R.id.myOrder_lease_Tv)
    TextView myOrderLeaseTv;
    @InjectView(R.id.myOrder_lease_Rl)
    RelativeLayout myOrderLeaseRl;
    @InjectView(R.id.myOrder_buy_Tv)
    TextView myOrderBuyTv;
    @InjectView(R.id.myOrder_buy_Rl)
    RelativeLayout myOrderBuyRl;
    @InjectView(R.id.myOrder_vip_Tv)
    TextView myOrderVipTv;
    @InjectView(R.id.myOrder_vip_Rl)
    RelativeLayout myOrderVipRl;
    @InjectView(R.id.myOrder_pager)
    ViewPager vp;


    private ArrayList<Fragment> list = new ArrayList<>();
    private MyMainAdapter adapter;
    private TextView[] Tvs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_order);
        ButterKnife.inject(this);
        BarTintManger.getBar(this);
        setViews();
        setListeners();
        initFragmentViewPager();
        setSelectedItem(0);
    }


    private void setViews(){
        Tvs=new TextView[]{myOrderLeaseTv,myOrderBuyTv,myOrderVipTv};
    }

    private void setListeners(){

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //租赁dingd
        myOrderLeaseRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedItem(0);
            }
        });

        //购买订单
        myOrderBuyRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedItem(1);
            }
        });

        //女神卡订单
        myOrderVipRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedItem(2);
            }
        });


    }


    /**
     * 初始化fragemt
     */
    private void initFragmentViewPager(){
        list.add(new MyLeaseOrderFragment());
        list.add(new BuyOrderFragment());
        list.add(new VipOrderFragment());
        adapter = new MyMainAdapter(getSupportFragmentManager(), list);
        vp.setOffscreenPageLimit(list.size());                        //设置幕后item的缓存数目
        vp.setAdapter(adapter);                             //给ViewPage设置适配器
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int count = Tvs.length;
                for (int i = 0; i < count; i++) {
                    if (position != 3 || i != 0) {
                        Tvs[(position + i) % count].setTextColor(getResources().getColor(i == 0 ? R.color.font19 : R.color.gray));
                    }else {
                        count += 1;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 页面选择
     * @param pos
     */
    public void setSelectedItem(int pos) {
        vp.setCurrentItem(pos, false);
    }
}
