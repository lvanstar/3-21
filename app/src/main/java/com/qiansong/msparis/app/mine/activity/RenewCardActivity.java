package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.mine.adapter.RenewCardAdapter;
import com.qiansong.msparis.app.mine.bean.RenewCardBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * kevin.cao
 * 续费页面
 */
public class RenewCardActivity extends BaseActivity {


    @InjectView(R.id.renew_card_type)
    TextView renewCardType;
    @InjectView(R.id.renew_card_time)
    TextView renewCardTime;
    @InjectView(R.id.renew_card_title)
    TextView renewCardTitle;
    @InjectView(R.id.renew_card_listview)
    GridView renewCardListview;
    @InjectView(R.id.renew_card_coupon_price)
    TextView renewCardCouponPrice;
    @InjectView(R.id.renew_card_coupon_layout)
    LinearLayout renewCardCouponLayout;
    @InjectView(R.id.renew_card_title1)
    TextView renewCardTitle1;
    @InjectView(R.id.renew_card_title2)
    TextView renewCardTitle2;
    @InjectView(R.id.renew_card_title3)
    TextView renewCardTitle3;
    @InjectView(R.id.renew_card_count_price)
    TextView renewCardCountPrice;
    @InjectView(R.id.renew_card_button)
    TextView renewCardButton;

    private RenewCardAdapter adapter;

    private ETitleBar titleBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renew_card);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
    }

    /**
     * 加载页面
     */
    public void initView() {
        adapter = new RenewCardAdapter(this);
        renewCardListview.setAdapter(adapter);
        //设置自适应高度
        ListUtils.setGridViewHeightBasedOnChildren(renewCardListview,1);

        List<RenewCardBean> list = new ArrayList<>();
        for (int i = 0; i <4; i++) {
            RenewCardBean bean = new RenewCardBean();
            list.add(bean);
        }
        adapter.setData(list);

        //设置自适应高度
        ListUtils.setGridViewHeightBasedOnChildren(renewCardListview,1);
        renewCardListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setIndex(position);
            }
        });

    }

    /**
     * 网络请求
     */
    public void requestData() {

    }

    /**
     * 填充数据
     */
    public void initData() {
    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("续费");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                RenewCardActivity.super.onBackPressed();
            }
        });
//        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent();
////                intent.setClass(AddressActivity.this, UpdateAddressActivity.class);
////                intent.putExtra(GlobalConsts.ADDRESS_TYPE,GlobalConsts.ADDRESS_TYPE_ADD);
////                startActivity(intent);
//            }
//        });
    }

    @OnClick({R.id.renew_card_coupon_layout, R.id.renew_card_button})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            //选择优惠券
            case R.id.renew_card_coupon_layout:
                break;
            //支付
            case R.id.renew_card_button:
                intent.setClass(RenewCardActivity.this, PayCardActivity.class);
                startActivity(intent);
                break;
        }
    }
}
