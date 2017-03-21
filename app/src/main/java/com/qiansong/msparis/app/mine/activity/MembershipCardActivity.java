package com.qiansong.msparis.app.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.CustomViewPager;
import com.qiansong.msparis.app.commom.adapter.MyMainAdapter;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.mine.fragment.BuyCardFragment;
import com.qiansong.msparis.app.mine.fragment.ExchangeCardFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * coalei
 * <p>
 * 购买女神卡
 */
public class MembershipCardActivity extends FragmentActivity {

    @InjectView(R.id.membership_title1)
    TextView membershipTitle1;
    @InjectView(R.id.membership_title2)
    TextView membershipTitle2;
    @InjectView(R.id.membership_fragment)
    CustomViewPager membershipFragment;
    private ETitleBar titleBar;

    private ArrayList<Fragment> list = new ArrayList<>();
    private MyMainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_card);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
        initData();
    }

    /**
     * 加载页面
     */
    public void initView() {
        list.add(new BuyCardFragment());
        list.add(new ExchangeCardFragment());
        adapter = new MyMainAdapter(getSupportFragmentManager(), list);
        membershipFragment.setOffscreenPageLimit(list.size());//设置幕后item的缓存数目
        membershipFragment.setAdapter(adapter);
        setSelectedItem(0);

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
        titleBar.setTitle("购买会员卡");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
//        titleBar.setRightTitle("积分明细");
//        titleBar.setRightTitleVisibility(View.VISIBLE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                MembershipCardActivity.super.onBackPressed();
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

    @OnClick({R.id.membership_title1, R.id.membership_title2})
    public void onClick(View view) {
        switch (view.getId()) {
            //购买
            case R.id.membership_title1:
                setSelectedItem(0);
                membershipTitle1.setTextColor(getResources().getColor(R.color.__picker_black_40));
                membershipTitle2.setTextColor(getResources().getColor(R.color.gray));
                break;
            //兑换
            case R.id.membership_title2:
                setSelectedItem(1);
                membershipTitle2.setTextColor(getResources().getColor(R.color.__picker_black_40));
                membershipTitle1.setTextColor(getResources().getColor(R.color.gray));
                break;
        }
    }
    /**
     * 选择页面
     *
     * @param pos
     */
    public void setSelectedItem(int pos) {
        membershipFragment.setCurrentItem(pos, false);
    }
}
