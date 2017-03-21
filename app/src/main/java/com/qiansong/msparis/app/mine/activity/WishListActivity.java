package com.qiansong.msparis.app.mine.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.app.commom.selfview.CustomViewPager;
import com.qiansong.msparis.app.mine.fragment.WishListAllFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.adapter.MyMainAdapter;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;

import java.util.ArrayList;

/**
 * 心愿单列表 - ye
 */
public class WishListActivity extends BaseActivity implements View.OnClickListener{
    private WishListActivity context;
    private TextView tb_1,tb_2,tb_3;
    private CustomViewPager my_wardrobe_vp;//page
    private MyMainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
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
        page_items.add(new WishListAllFragment("0"));
        page_items.add(new WishListAllFragment("1"));
        page_items.add(new WishListAllFragment("2"));
        adapter = new MyMainAdapter(getSupportFragmentManager(), page_items);
        my_wardrobe_vp.setOffscreenPageLimit(page_items.size());                        //设置幕后item的缓存数目
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
        titleBar.setTitle("心愿单");//设置标题
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
//                startActivity(new Intent(context,ClothingRecordActivity.class));
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
