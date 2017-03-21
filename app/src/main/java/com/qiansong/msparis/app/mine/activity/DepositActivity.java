package com.qiansong.msparis.app.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.CustomViewPager;
import com.qiansong.msparis.app.commom.adapter.MyMainAdapter;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.mine.fragment.ExperienceCardFragment;
import com.qiansong.msparis.app.mine.fragment.LeaseOrderFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * coalei
 * <p>
 * 我的押金
 */
public class DepositActivity extends BaseActivity {


    @InjectView(R.id.deposit_title1)
    TextView depositTitle1;
    @InjectView(R.id.deposit_title2)
    TextView depositTitle2;
    @InjectView(R.id.deposit_fragment)
    CustomViewPager depositFragment;
    @InjectView(R.id.deposit_title)
    TextView depositTitle;
    private ETitleBar titleBar;

    private ArrayList<Fragment> list = new ArrayList<>();
    private MyMainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
    }

    /**
     * 加载页面
     */
    public void initView() {

        list.add(new LeaseOrderFragment());
        list.add(new ExperienceCardFragment());
        adapter = new MyMainAdapter(getSupportFragmentManager(), list);
        depositFragment.setOffscreenPageLimit(list.size());//设置幕后item的缓存数目
        depositFragment.setAdapter(adapter);

        setSelectedItem(0);

    }


    @OnClick({R.id.deposit_title1, R.id.deposit_title2})
    public void onClick(View view) {
        switch (view.getId()) {
            //租赁订单
            case R.id.deposit_title1:
                setSelectedItem(0);
                depositTitle1.setTextColor(getResources().getColor(R.color.__picker_black_40));
                depositTitle2.setTextColor(getResources().getColor(R.color.gray));
                break;
            //体验卡订单
            case R.id.deposit_title2:
                setSelectedItem(1);
                depositTitle2.setTextColor(getResources().getColor(R.color.__picker_black_40));
                depositTitle1.setTextColor(getResources().getColor(R.color.gray));
                break;
        }
    }
    /**
     * 选择页面
     *
     * @param pos
     */
    public void setSelectedItem(int pos) {
        depositFragment.setCurrentItem(pos, false);
    }
    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("我的押金");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setRightTitle("退款说明");
        titleBar.setRightTitleVisibility(View.VISIBLE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                DepositActivity.super.onBackPressed();
            }
        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(DepositActivity.this, WebViewActivity.class);
//                startActivity(intent);
            }
        });
    }

}
