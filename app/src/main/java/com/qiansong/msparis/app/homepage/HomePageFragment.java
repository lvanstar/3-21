package com.qiansong.msparis.app.homepage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.selfview.CustomViewPager;
import com.qiansong.msparis.app.commom.util.AccountUtil;
import com.qiansong.msparis.app.homepage.activity.MessageCenterActivity;
import com.qiansong.msparis.app.homepage.fragment.BrandFragment;
import com.qiansong.msparis.app.homepage.fragment.ChoiceFragment;
import com.qiansong.msparis.app.commom.adapter.MyMainAdapter;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.homepage.fragment.SpecialFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by lizhaozhao on 2017/2/4.
 *
 * 首页
 */

public class HomePageFragment extends BaseFragment implements View.OnClickListener{

    private View view;
    CustomViewPager homepage_vp;//首页page
    private MyMainAdapter adapter;

    TextView choice_txt,brand_txt,special_txt;
    public static ImageView messageIv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_homepage,null);
        ButterKnife.inject(this,view);
//        setTitleBar();
        setID();
        setListener();
        init();
        return view;
    }

    private void setListener() {
        homepage_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    choice_txt.setTextColor(getContext().getResources().getColor(R.color.font19));
                    brand_txt.setTextColor(getContext().getResources().getColor(R.color.font20));
                    special_txt.setTextColor(getContext().getResources().getColor(R.color.font20));
                }else if (position==1){
                    choice_txt.setTextColor(getContext().getResources().getColor(R.color.font20));
                    brand_txt.setTextColor(getContext().getResources().getColor(R.color.font19));
                    special_txt.setTextColor(getContext().getResources().getColor(R.color.font20));
                }else if (position==2){
                    choice_txt.setTextColor(getContext().getResources().getColor(R.color.font20));
                    brand_txt.setTextColor(getContext().getResources().getColor(R.color.font20));
                    special_txt.setTextColor(getContext().getResources().getColor(R.color.font19));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        view.findViewById(R.id.click_0).setOnClickListener(this);
        view.findViewById(R.id.click_1).setOnClickListener(this);
        view.findViewById(R.id.click_2).setOnClickListener(this);
    }


    //填充
    private void init() {
        ArrayList<Fragment> page_items=new ArrayList<>();
        page_items.add(new ChoiceFragment());
        page_items.add(new BrandFragment());
        page_items.add(new SpecialFragment());
        adapter = new MyMainAdapter(getChildFragmentManager(), page_items);
        homepage_vp.setOffscreenPageLimit(page_items.size());                        //设置幕后item的缓存数目
        homepage_vp.setAdapter(adapter);
        setSelectedItem(0);
    }

    private void setID() {
        homepage_vp= (CustomViewPager) view.findViewById(R.id.homepage_vp);
        choice_txt= (TextView) view.findViewById(R.id.choice_txt);
        brand_txt= (TextView) view.findViewById(R.id.brand_txt);
        special_txt= (TextView) view.findViewById(R.id.special_txt);
        messageIv= (ImageView) view.findViewById(R.id.right_image_2);


        messageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AccountUtil.showLoginView(getActivity()))return;
                startActivity(new Intent(getActivity(), MessageCenterActivity.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
       int id=view.getId();
        switch (id){
            case R.id.click_0:setSelectedItem(0);break;
            case R.id.click_1:setSelectedItem(1);break;
            case R.id.click_2:setSelectedItem(2);break;
        }
    }

    /**
     * 选择页面
     */
    public void setSelectedItem(int pos) {
        homepage_vp.setCurrentItem(pos, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
        if(MyApplication.isMessAGEONE||MyApplication.isMessAGETWO||MyApplication.isMessAGETHREE){
            messageIv.setImageResource(R.mipmap.homepage_message_red);
        }else {
            messageIv.setImageResource(R.mipmap.homepage_message);
        }
    }

    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar) view.findViewById(R.id.title_bar);
        titleBar.setTitle("女神派");//设置标题
        titleBar.setTitleColor(getContext().getResources().getColor(R.color.font19));
//        titleBar.setLeftImageResource(R.mipmap.ic_launcher);//设置左边图标
//        titleBar.setRightImageResource(R.mipmap.ic_launcher);//设置右边图标
        titleBar.setBackgroundColor(Color.parseColor("#ffffff"));//设置背景
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
            }
        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
            @Override
            public void onClick(View view) {
            }
        });
    }



}
