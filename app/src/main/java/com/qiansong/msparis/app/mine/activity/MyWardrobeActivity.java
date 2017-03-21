package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.CustomViewPager;
import com.qiansong.msparis.app.mine.fragment.OnceFragment;
import com.qiansong.msparis.app.commom.adapter.MyMainAdapter;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.mine.fragment.BookingFragment;
import com.qiansong.msparis.app.mine.fragment.NowAccompaniedFragment;

import java.util.ArrayList;

/**
 * 我的衣橱-叶
 */
public class MyWardrobeActivity extends BaseActivity implements View.OnClickListener{
    private MyWardrobeActivity context;
    private TextView tb_1,tb_2,tb_3;
    private CustomViewPager my_wardrobe_vp;//page
    private MyMainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wardrobe);
        context=this;
        setTitleBar();
        init();
        setListener();
    }

    private void setListener() {
        tb_1.setOnClickListener(this);
        tb_2.setOnClickListener(this);
        tb_3.setOnClickListener(this);
        my_wardrobe_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
               switch (position){
                   case 0:
                       tb_1.setTextColor(context.getResources().getColor(R.color.font19));
                       tb_2.setTextColor(context.getResources().getColor(R.color.font20));
                       tb_3.setTextColor(context.getResources().getColor(R.color.font20));
                       break;
                   case 1:
                       tb_1.setTextColor(context.getResources().getColor(R.color.font20));
                       tb_2.setTextColor(context.getResources().getColor(R.color.font19));
                       tb_3.setTextColor(context.getResources().getColor(R.color.font20));
                       break;
                   case 2:
                       tb_1.setTextColor(context.getResources().getColor(R.color.font20));
                       tb_2.setTextColor(context.getResources().getColor(R.color.font20));
                       tb_3.setTextColor(context.getResources().getColor(R.color.font19));
                       break;
               }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    private void init() {
        tb_1= (TextView) findViewById(R.id.tb_1);
        tb_2= (TextView) findViewById(R.id.tb_2);
        tb_3= (TextView) findViewById(R.id.tb_3);
        my_wardrobe_vp= (CustomViewPager)findViewById(R.id.my_wardrobe_vp);
        ArrayList<Fragment> page_items=new ArrayList<>();
        page_items.add(new NowAccompaniedFragment());
        page_items.add(new BookingFragment());
        page_items.add(new OnceFragment());
        adapter = new MyMainAdapter(getSupportFragmentManager(), page_items);
        my_wardrobe_vp.setOffscreenPageLimit(page_items.size());  //设置幕后item的缓存数目
//        my_wardrobe_vp.setPagingEnabled(true);
        my_wardrobe_vp.setAdapter(adapter);
        setSelectedItem(0);

    }

    /**
     * 选择页面
     */
    public void setSelectedItem(int pos) {
        my_wardrobe_vp.setCurrentItem(pos, false);
    }
    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("我的衣橱");//设置标题
        titleBar.setTitleColor(context.getResources().getColor(R.color.font19));
        titleBar.setLeftImageResource(R.mipmap.back);//设置左边图标
        titleBar.setRightTitle("还衣记录");//设置右边文字
//        titleBar.setLeftTitle("返回");//设置左边文字
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
                startActivity(new Intent(context,ClothingRecordActivity.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tb_1:setSelectedItem(0);break;
            case R.id.tb_2:setSelectedItem(1);break;
            case R.id.tb_3:setSelectedItem(2);break;
        }
    }
}
