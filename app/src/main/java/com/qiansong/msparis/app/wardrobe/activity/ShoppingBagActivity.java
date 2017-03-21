package com.qiansong.msparis.app.wardrobe.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.app.wardrobe.fragment.ShoppingBagFulldressFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.adapter.MyMainAdapter;
import com.qiansong.msparis.app.commom.selfview.CustomViewPager;
import com.qiansong.msparis.app.wardrobe.fragment.ShoppingBagDailyFragment;

import java.util.ArrayList;

/**
 * 购物袋 + 购物车
 */
public class ShoppingBagActivity extends BaseActivity implements View.OnClickListener{
    public static String intentKey="ShoppingBagActivity";
    private String type="1";//"1" 日常购物袋   "2"礼服购物车
    private CustomViewPager shopp_bag_vp;
    private MyMainAdapter adapter;
    private TextView daily_txt,fulldress_txt;
    public View activity_shopping_bag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_bag);
        type=getIntent().getStringExtra(intentKey);
        if (null==type)type="1";
        init();setListener();
    }
    //填充
    private void init() {
        activity_shopping_bag=findViewById(R.id.activity_shopping_bag);

        shopp_bag_vp= (CustomViewPager) findViewById(R.id.shopp_bag_vp);
        daily_txt= (TextView) findViewById(R.id.daily_txt);
        fulldress_txt= (TextView) findViewById(R.id.fulldress_txt);
        ArrayList<Fragment> page_items=new ArrayList<>();
        page_items.add(new ShoppingBagDailyFragment());
        page_items.add(new ShoppingBagFulldressFragment());
        adapter = new MyMainAdapter(getSupportFragmentManager(), page_items);
        shopp_bag_vp.setOffscreenPageLimit(page_items.size());                        //设置幕后item的缓存数目
        shopp_bag_vp.setAdapter(adapter);
        setSelectedItem("2".equals(type)?1:0);
        if (shopp_bag_vp.getCurrentItem()==0){
            daily_txt.setTextColor(getResources().getColor(R.color.font19));
            fulldress_txt.setTextColor(getResources().getColor(R.color.font20));
        }else if (shopp_bag_vp.getCurrentItem()==1){
            daily_txt.setTextColor(getResources().getColor(R.color.font20));
            fulldress_txt.setTextColor(getResources().getColor(R.color.font19));
        }
    }
    private void setListener() {
        shopp_bag_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    daily_txt.setTextColor(getResources().getColor(R.color.font19));
                    fulldress_txt.setTextColor(getResources().getColor(R.color.font20));
                }else if (position==1){
                    daily_txt.setTextColor(getResources().getColor(R.color.font20));
                    fulldress_txt.setTextColor(getResources().getColor(R.color.font19));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        findViewById(R.id.click_0).setOnClickListener(this);
        findViewById(R.id.click_1).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.click_0:setSelectedItem(0);break;
            case R.id.click_1:setSelectedItem(1);break;
            case R.id.back:finish();break;
        }
    }
    /**
     * 选择页面
     *
     * @param pos
     */
    public void setSelectedItem(int pos) {
        shopp_bag_vp.setCurrentItem(pos, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode==31) finish();
    }
}
