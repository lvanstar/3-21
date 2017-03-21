package com.qiansong.msparis.app.commom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.BannerBean;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.adapter.GuideAdapter;
import com.qiansong.msparis.app.commom.selfview.TXCirclePageIndicator;
import com.qiansong.msparis.app.commom.util.BarTintManger;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.ToastUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/2/6.
 * <p>
 * 引导页
 */

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener{

    @InjectView(R.id.pager)
    ViewPager bannerPager;
    @InjectView(R.id.indicator)
    TXCirclePageIndicator indicator;
    @InjectView(R.id.btnEnter)
    Button btnEnter;

    /** 引导页 适配器 */
    private GuideAdapter guideBannerAdapter;
    /** 引导页 图片集合 */
    private ArrayList<BannerBean> bannerBeans;
    private AnimationSet showAnimation;
    private AnimationSet hiddenAnimation;
    private GuideActivity INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.inject(this);
        BarTintManger.getBar(this);
        INSTANCE=this;
        initView();
        setViews();
        setListeners();
    }


    private void initView(){
        indicator.setOnPageChangeListener(this);

        indicator.setFillColor(getResources().getColor(R.color.color_f964b3));

        showAnimation = new AnimationSet(true);
        ScaleAnimation scaleShow = new ScaleAnimation(0.2f, 1.0f, 0.2f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AlphaAnimation alphaShow = new AlphaAnimation(0.1f, 1.0f);
        showAnimation.addAnimation(alphaShow);
        showAnimation.addAnimation(scaleShow);
        showAnimation.setInterpolator(new OvershootInterpolator());
        showAnimation.setDuration(600);

        hiddenAnimation = new AnimationSet(true);
        ScaleAnimation scaleHidden = new ScaleAnimation(1.0f, 0.2f, 1.0f, 0.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AlphaAnimation alphaHidden = new AlphaAnimation(1.0f, 0.1f);
        hiddenAnimation.addAnimation(alphaHidden);
        hiddenAnimation.addAnimation(scaleHidden);
        hiddenAnimation.setDuration(300);
    }

    private void setViews(){

        bannerBeans = new ArrayList<BannerBean>();
        int[] bannerList = new int[] { R.mipmap.icon_guide1,
                R.mipmap.icon_guide2,
                R.mipmap.icon_guide3,
                R.mipmap.icon_guide4,
        };

        for (int i = 0; i < bannerList.length; i++) {
            BannerBean bannerBean = new BannerBean();
            bannerBean.img_srcxx = bannerList[i];
            bannerBeans.add(bannerBean);
        }

        guideBannerAdapter = new GuideAdapter(this);
        guideBannerAdapter.setBannerList(bannerBeans);
        guideBannerAdapter.notifyDataSetChanged();

        bannerPager.setAdapter(guideBannerAdapter);
        indicator.setViewPager(bannerPager);
    }

    private void setListeners(){

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.isFirst=true;
                TXShareFileUtil.getInstance().putBoolean(GlobalConsts.IS_FIRST,true);
                startActivity(new Intent(INSTANCE, MainActivity.class));
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == guideBannerAdapter.getCount() - 1) {
            btnEnter.startAnimation(showAnimation);
            btnEnter.setVisibility(View.VISIBLE);
            indicator.setVisibility(View.GONE);
        } else if (btnEnter.getVisibility() == View.VISIBLE) {
            btnEnter.startAnimation(hiddenAnimation);
            btnEnter.setVisibility(View.GONE);
            indicator.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
